package com.hdyl.schedule.xxljob.handler;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hdyl.schedule.xxljob.common.config.JoinPaySftpConfig;
import com.hdyl.schedule.xxljob.entity.pojo.*;
import com.hdyl.schedule.xxljob.mapper.AllocFundsFileMapper;
import com.hdyl.schedule.xxljob.mapper.AllocFundsLogMapper;
import com.hdyl.schedule.xxljob.mapper.WalletOrderInfoMapper;
import com.hdyl.schedule.xxljob.mapper.WalletOrderMapper;
import com.hdyl.schedule.xxljob.util.SftpUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 汇聚相关对账处理器
 *
 * @author qigaoxin
 * @date 2020年9月22日
 */
@Slf4j
@Component
public class JoinPayCheckHandler {

    @Autowired
    private WalletOrderInfoMapper walletOrderInfoMapper;

    @Autowired
    private JoinPaySftpConfig joinPaySftpConfig;

    @Autowired
    private AllocFundsLogMapper allocFundsLogMapper;

    @Autowired
    private AllocFundsFileMapper allocFundsFileMapper;


    @XxlJob("checkYesterdayPaymentBill")
    public ReturnT<String> checkYesterdayPaymentBill(String params) {
        String yesterday = DateUtil.yesterday().toString("yyyyMMdd");

        SftpUtil joinPaySftpClient = new SftpUtil(joinPaySftpConfig.getUsername(), joinPaySftpConfig.getPassword(), joinPaySftpConfig.getHost(), joinPaySftpConfig.getPort());
        String billFileName = joinPaySftpConfig.getUsername() + "_allocate_paymentorder_" + yesterday + ".csv";
        ICsvMapReader inFile;
        String ossFilePath;
        try {
            joinPaySftpClient.login();
            // 下载汇聚对账文件
            final String directory = String.format("/upload/trade_file/%s/%s/%s", yesterday.substring(0, 4), yesterday.substring(4, 6), yesterday.substring(6, 8));
            joinPaySftpClient.download(directory, billFileName, billFileName);
            // 对账文件上传至阿里云 OSS
            String ossResultJson = HttpUtil.createPost("https://xdz.hdyl.net.cn/api/oss/upload").form("file", new File(billFileName)).execute().body();
            JSONObject ossResultObject = JSON.parseObject(ossResultJson);
            ossFilePath = ossResultObject.getString("data");
            inFile = new CsvMapReader(new FileReader("/" + billFileName), CsvPreference.STANDARD_PREFERENCE);
        } catch (Exception e) {
            log.error("支付对账文件拉取/上传失败, 失败原因：{}", JSON.toJSONString(e));
            joinPaySftpClient.logout();
            return ReturnT.FAIL;
        }
        try {
            String[] header = inFile.getHeader(true);
            List<JoinPayAllocatePaymentOrderCsv> fundsList = new ArrayList<>(100);
            while (JoinPayAllocatePaymentOrderCsv.getPageAllocFundsList(fundsList, inFile, header)) {
                fundsList.parallelStream().forEach(platformAllocFund -> {
                    JSONObject checkResult = this.checkAllocFunds(platformAllocFund);
                    if (!checkResult.getBoolean("success")) {
                        log.error("单条对账失败！！！\n订单号：{}, 失败原因：{}\n", platformAllocFund.getWalletOrderCode(), checkResult.getString("message"));
                    }
                });
                fundsList = new ArrayList<>(100);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ReturnT.FAIL;
        } finally {
            try {
                inFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            File delPoint = new File(billFileName);
            if (delPoint.isFile() && delPoint.exists()) {
                delPoint.delete();
            }
        }
        // 存储对账文件条目
        AllocFundsFileEntity allocFundsFileEntity = new AllocFundsFileEntity();
        allocFundsFileEntity.setBillName(billFileName);
        allocFundsFileEntity.setBillAmount(allocFundsLogMapper.sumBillAmountByDate(yesterday));
        allocFundsFileEntity.setBillNumber(allocFundsLogMapper.sumBillNumberByDate(yesterday));
        allocFundsFileEntity.setBillDate(Integer.parseInt(yesterday));
        allocFundsFileEntity.setIsDiff(allocFundsLogMapper.isDiffByDate(yesterday)==0?0:1);
        allocFundsFileEntity.setFilePath(ossFilePath);
        allocFundsFileEntity.setDiffAmountNumber(allocFundsLogMapper.sumDiffAmountNumberByDate(yesterday));
        allocFundsFileEntity.setDiffRateNumber(allocFundsLogMapper.sumDiffFeeNumberByDate(yesterday));
        allocFundsFileEntity.setDiffOtherNumber(allocFundsLogMapper.sumDiffOtherNumberByDate(yesterday));
        allocFundsFileEntity.setVerifyTime(new Date());
        allocFundsFileEntity.setCreateTime(new Date());
        allocFundsFileEntity.setUpdateTime(new Date());
        allocFundsFileMapper.save(allocFundsFileEntity);

        return ReturnT.SUCCESS;
    }

    private JSONObject checkAllocFunds(JoinPayAllocatePaymentOrderCsv platformAllocFund) {
        try {
            AllocFundsLogEntity allocFundsLogEntity = new AllocFundsLogEntity();
            WalletOrderInfoEntity wallet = walletOrderInfoMapper.selectOrderByWalletOrderCode(platformAllocFund.getWalletOrderCode());

            boolean forceJump = false;
            allocFundsLogEntity.setIsException(0);

            // 订单号对比 -> 对比出补单漏单情况
            if (ObjectUtils.isEmpty(wallet)) {
                // 出现差异 -> 补单差异 (汇聚有, 我们没有)
                allocFundsLogEntity.setExceptionType(3);
                allocFundsLogEntity.setIsException(1);
                wallet = new WalletOrderInfoEntity();
                forceJump = true;
            }
            // 交易金额对比 -> 对比出交易金额不一致情况
            if (!forceJump && !wallet.getActualAmount().stripTrailingZeros().equals(platformAllocFund.getPayAmount().stripTrailingZeros())) {
                // 出现差异 -> 交易金额不同
                allocFundsLogEntity.setExceptionType(1);
                allocFundsLogEntity.setIsException(1);
                BigDecimal diff = platformAllocFund.getOrderAmount().subtract(wallet.getActualAmount());
                allocFundsLogEntity.setExceptionDiffAmount(diff);
            }
            // 手续费金额对比 -> 对比出手续费金额不一致情况
            BigDecimal payChannelFee = ObjectUtils.isEmpty(wallet.getPayChannelFee()) ? new BigDecimal(0) : wallet.getPayChannelFee();
            if (!forceJump && !payChannelFee.stripTrailingZeros().equals(platformAllocFund.getFee().stripTrailingZeros())) {
                // 出现差异 -> 交易金额不同
                allocFundsLogEntity.setExceptionType(2);
                allocFundsLogEntity.setIsException(1);
                BigDecimal diff = platformAllocFund.getFee().subtract(wallet.getPayChannelFee());
                allocFundsLogEntity.setExceptionDiffAmount(diff);
            }

            allocFundsLogEntity.setTradeNo(platformAllocFund.getWalletOrderCode());
            allocFundsLogEntity.setWalletOrderCode(platformAllocFund.getWalletOrderCode());
            allocFundsLogEntity.setPlatfromPaymentNo(platformAllocFund.getPlatformPaymentNo());
            allocFundsLogEntity.setPlatformLedgerNo(null);
            allocFundsLogEntity.setPlatformOrderAmount(platformAllocFund.getPayAmount());
            allocFundsLogEntity.setPlatformFee(platformAllocFund.getFee());
            allocFundsLogEntity.setPlatformLedgerAmount(platformAllocFund.getLedgerAmount());
            allocFundsLogEntity.setOrderAmount(ObjectUtils.isNotEmpty(wallet.getActualAmount()) ? wallet.getActualAmount() : null);
            allocFundsLogEntity.setLedgerAmount(wallet.getMerchantReceivable());
            allocFundsLogEntity.setFee(wallet.getPayChannelFee());
            // 存在问题, 应该按汇聚为主
            allocFundsLogEntity.setOrderType(1);
            allocFundsLogEntity.setOrderStatus(1);
            allocFundsLogEntity.setMerchantId(wallet.getMerchantId());
            allocFundsLogEntity.setUserId(wallet.getMerchantUserId());
            allocFundsLogEntity.setOrderTime(platformAllocFund.getCreateTime());
            allocFundsLogEntity.setGroupDate(Integer.parseInt(DateUtil.format(platformAllocFund.getPayTime(), "yyyyMMdd")));
            // 保存
            allocFundsLogMapper.save(allocFundsLogEntity);
        } catch (Exception e) {
            return new JSONObject().fluentPut("success", false).fluentPut("message", JSON.toJSONString(e));
        }
        return new JSONObject().fluentPut("success", true);
    }

}
