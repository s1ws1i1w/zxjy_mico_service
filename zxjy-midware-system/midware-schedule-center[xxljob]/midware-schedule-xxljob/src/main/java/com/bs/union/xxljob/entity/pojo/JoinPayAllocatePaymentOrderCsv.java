package com.hdyl.schedule.xxljob.entity.pojo;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.ICsvMapReader;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 汇聚分账核对 CSV
 *
 * @author qigaoxin
 * @date 2020年9月20日
 */
@Data
public class JoinPayAllocatePaymentOrderCsv implements Serializable {

    @ApiModelProperty("创建时间")
    @JSONField(name = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty("支付")
    @JSONField(name = "支付时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    @ApiModelProperty("记账时间")
    @JSONField(name = "记账时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date recordTime;

    @ApiModelProperty("平台商家号")
    @JSONField(name = "平台商户编号\n")
    private Long platformMerchantNo;

    @ApiModelProperty("平台商家名称")
    @JSONField(name = "分账商户名称")
    private String platformMerchantName;

    @ApiModelProperty("平台分账状态 - 分账成功, 分账失败")
    @JSONField(name = "分账状态")
    private String platformLedgerStatus;

    @ApiModelProperty("订单号")
    @JSONField(name = "商户订单号")
    private String walletOrderCode;

    @ApiModelProperty("平台分账订单号")
    @JSONField(name = "分账订单号")
    private Long platformLedgerNo;

    @ApiModelProperty("平台交易流水号")
    @JSONField(name = "支付流水号")
    private Long platformPaymentNo;

    @ApiModelProperty("银行流水号")
    @JSONField(name = "银行流水号")
    private String bankTurnNo;

    @ApiModelProperty("订单总金额")
    @JSONField(name = "订单营销总金额")
    private BigDecimal orderAmount;

    @ApiModelProperty("支付金额")
    @JSONField(name = "支付金额")
    private BigDecimal payAmount;

    @ApiModelProperty("全部手续费")
    @JSONField(name = "手续费")
    private BigDecimal fee;

    @ApiModelProperty("商品名称")
    @JSONField(name = "商品名称")
    private String productName;

    @ApiModelProperty("分账后所得金额")
    @JSONField(name = "分账金额")
    private BigDecimal ledgerAmount;

    @ApiModelProperty("支付方式")
    @JSONField(name = "支付方式")
    private String payType;

    @ApiModelProperty("支付产品类型")
    @JSONField(name = "支付产品")
    private String payChannel;

    @ApiModelProperty("公传信息")
    @JSONField(name = "公传信息")
    private String publicInfo;

    @ApiModelProperty("分账类型 - 实时分账")
    @JSONField(name = "分账类型")
    private String ledgerType;

    public static final CellProcessor[] allocFundsProcessors = new CellProcessor[] {
            new ParseDate("yyyy-MM-dd HH:mm:ss"),
            null,
            null,
            null,
            null,
            new Optional(),
            null,
            null,
            null,
            null,
            null,
            null,
            null
    };

    public static JoinPayAllocatePaymentOrderCsv mapToBean(Map<String, String> map) {
        map.forEach((k, v) -> {
            if (ObjectUtil.isNotNull(v)) {
                map.put(k, v.trim());
            }
        });
        String mapJson = JSON.toJSONString(map);
        return JSON.parseObject(mapJson, JoinPayAllocatePaymentOrderCsv.class);
    }

    public static boolean getPageAllocFundsList(List<JoinPayAllocatePaymentOrderCsv> fundsList, ICsvMapReader inFile, String[] header) throws IOException, IOException {
        int index = 0;
        boolean status = false;
        List<JoinPayAllocatePaymentOrderCsv> csvBeans = new ArrayList<>();
        Map<String, String> map;

        while ((map = inFile.read(header)) != null) {
            JoinPayAllocatePaymentOrderCsv joinpayAllocFundsCsv = JoinPayAllocatePaymentOrderCsv.mapToBean(map);
            csvBeans.add(joinpayAllocFundsCsv);
            index++;
            if (index == 100) {
                break;
            }
        }
        if(!csvBeans.isEmpty()){
            fundsList.addAll(csvBeans);
            status = true;
        }
        return status;
    }

}
