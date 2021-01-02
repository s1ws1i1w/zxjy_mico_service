package com.bs.union.midware.xxljob.manager.impl;

import com.alibaba.fastjson.JSONObject;
import com.hdyl.merchant.union.common.utils.DingTalkUtil;
import com.bs.union.midware.xxljob.common.constants.RedisConstant;
import com.hdyl.schedule.xxljob.entity.pojo.*;
import com.bs.union.midware.xxljob.manager.WalletOrderRefundTransferManagerService;
import com.hdyl.schedule.xxljob.service.*;
import com.bs.union.midware.xxljob.threadpool.ScheduleTransferOrderThreadPoolManager;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Godzilla
 * @create 2018-10-23 16:53
 */
@Service
@Slf4j
public class WalletOrderRefundTransferManagerServiceImpl implements WalletOrderRefundTransferManagerService {

    private final WalletOrderService walletOrderService;
    private final WalletOrderInfoService walletOrderInfoService;
    private final WalletOrderDetailService walletOrderDetailService;
    private final WalletOrderRefundService walletOrderRefundService;
    private final CmsMerchantService merchantService;
    private final CmsIndustryService industryService;
    private final CmsMerchantUserService merchantUserService;
    private final StringRedisTemplate redisTemplate;

    public WalletOrderRefundTransferManagerServiceImpl(WalletOrderService walletOrderService, WalletOrderInfoService walletOrderInfoService, WalletOrderDetailService walletOrderDetailService, WalletOrderRefundService walletOrderRefundService, CmsMerchantService merchantService, CmsIndustryService industryService, CmsMerchantUserService merchantUserService, StringRedisTemplate redisTemplate) {
        this.walletOrderService = walletOrderService;
        this.walletOrderInfoService = walletOrderInfoService;
        this.walletOrderDetailService = walletOrderDetailService;
        this.walletOrderRefundService = walletOrderRefundService;
        this.merchantService = merchantService;
        this.industryService = industryService;
        this.merchantUserService = merchantUserService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void walletOrderRefundTransfer(Integer minId, Integer pageSize, Integer batch) {
        if (minId == -1) {
            String minIdStr = redisTemplate.opsForValue().get(RedisConstant.WALLET_ORDER_REFUND_MIN_ID);
            if (StringUtils.isNotBlank(minIdStr)) {
                minId = Integer.parseInt(minIdStr) + 1;
            } else {
                minId = 0;
            }
        }
        Integer maxId = walletOrderRefundService.selectMaxId();
        // 多线程处理
        int batchSize = (maxId - minId) / batch;
        List<FutureTask<JSONObject>> futureTaskList = new ArrayList<>();
        for (int i = 0; i < batch; i++) {
            TransferOrderRefundThread task = new TransferOrderRefundThread(walletOrderService, walletOrderInfoService, walletOrderDetailService, walletOrderRefundService, merchantService, industryService, merchantUserService, i * batchSize + 1 , (i+1) * batchSize);
            FutureTask<JSONObject> futureTask = new FutureTask<>(task);
            futureTaskList.add(futureTask);
            ScheduleTransferOrderThreadPoolManager.getExecutorManager().submit(futureTask);
        }
        if ((maxId - minId) % batch > 0) {
            TransferOrderRefundThread task = new TransferOrderRefundThread(walletOrderService, walletOrderInfoService, walletOrderDetailService, walletOrderRefundService, merchantService, industryService, merchantUserService,batch * batchSize + 1 , maxId);
            FutureTask<JSONObject> futureTask = new FutureTask<>(task);
            futureTaskList.add(futureTask);
            ScheduleTransferOrderThreadPoolManager.getExecutorManager().submit(futureTask);
        }
        Integer newNum = 0;
        int count = 0;
        for(FutureTask<JSONObject> futureTask : futureTaskList) {
            try {
                JSONObject jsonObject = futureTask.get();
                count += jsonObject.getInteger("count");
                newNum += jsonObject.getInteger("newNum");
                log.info("当前同步数量：{}, 入库新表记录数量:{}", count, newNum);
            } catch (InterruptedException | ExecutionException e) {
                log.error("多线程结果获取异常", e);
            }
        }
        XxlJobLogger.log("最大id:{},同步数量:{}，入库新表记录数量:{}", maxId, count, newNum);
        // 保存当前批次迁移的最大订单记录id，下次拉取订单数据从该记录+1的位置开始拉
        redisTemplate.opsForValue().set(RedisConstant.WALLET_ORDER_REFUND_MIN_ID, String.valueOf(maxId));
    }

    static class TransferOrderRefundThread implements Callable<JSONObject> {
        private final WalletOrderService walletOrderService;
        private final WalletOrderInfoService walletOrderInfoService;
        private final WalletOrderDetailService walletOrderDetailService;
        private final WalletOrderRefundService walletOrderRefundService;
        private final CmsMerchantService merchantService;
        private final CmsIndustryService industryService;
        private final CmsMerchantUserService merchantUserService;
        private final Integer minId;
        private final Integer maxId;

        TransferOrderRefundThread(WalletOrderService walletOrderService, WalletOrderInfoService walletOrderInfoService, WalletOrderDetailService walletOrderDetailService, WalletOrderRefundService walletOrderRefundService, CmsMerchantService merchantService, CmsIndustryService industryService, CmsMerchantUserService merchantUserService, Integer minId, Integer maxId) {
            this.walletOrderService = walletOrderService;
            this.walletOrderInfoService = walletOrderInfoService;
            this.walletOrderDetailService = walletOrderDetailService;
            this.walletOrderRefundService = walletOrderRefundService;
            this.merchantService = merchantService;
            this.industryService = industryService;
            this.merchantUserService = merchantUserService;
            this.minId = minId;
            this.maxId = maxId;
        }

        @Override
        public JSONObject call() throws Exception {
            JSONObject jsonObject = new JSONObject();
            int count = 0;
            int newNum = 0;
            int pageSize = 1000;
            int pages = (maxId - minId) % pageSize > 0 ? (maxId - minId) / pageSize + 1 : (maxId - minId) / pageSize;
            for (int i = 0; i < pages; i++) {
                List<WalletOrderRefundEntity> walletOrderList = walletOrderRefundService.selectBetweenId(minId, maxId);
                count += walletOrderList.size();
                if (walletOrderList.size() > 0) {
                    for (WalletOrderRefundEntity walletOrderRefundEntity : walletOrderList) {
                        JSONObject dtoObject = this.convertDTO(walletOrderRefundEntity);
                        if (dtoObject != null && dtoObject.get("info") != null) {
                            WalletOrderInfoEntity orderInfoEntity = (WalletOrderInfoEntity) dtoObject.get("info");
                            walletOrderInfoService.insert(orderInfoEntity);
                            if (dtoObject.get("detail") != null) {
                                WalletOrderDetailEntity orderDetailEntity = (WalletOrderDetailEntity) dtoObject.get("detail");
                                orderDetailEntity.setOrderId(orderInfoEntity.getId());
                                walletOrderDetailService.add(orderDetailEntity);
                            }
                            newNum++;
                        }
                    }
                }
            }
            jsonObject.put("newNum", newNum);
            jsonObject.put("count", count);
            return jsonObject;
        }

        /**
         * 实体类型转换
         * @param orderRefundEntity
         * @return
         */
        private JSONObject convertDTO(WalletOrderRefundEntity orderRefundEntity) {
            // 付款记录信息填充
            WalletOrderEntity walletOrderEntity = walletOrderService.selectByWalletOrderCode(orderRefundEntity.getMallOrderCode());
            if (walletOrderEntity == null) {
                log.error("退款订单没有获取到具体的订单信息:{}", orderRefundEntity.getMallOrderCode());
                DingTalkUtil.sendTextMessage("退款订单没有获取到具体的订单信息，orderCode:" + orderRefundEntity.getMallOrderCode(), DingTalkUtil.SHENGBAO_ERROR_YABA);
                return null;
            }
            // 付款记录
            WalletOrderInfoEntity info = new WalletOrderInfoEntity();
            info.setOrderId(walletOrderEntity.getId());
            info.setActualAmount(walletOrderEntity.getActualAmount());
            info.setCreateTime(walletOrderEntity.getCreateTime());
            info.setDealId(walletOrderEntity.getDealId());
            info.setMerchantId(walletOrderEntity.getMerchantId());
            info.setMerchantReceivable(walletOrderEntity.getMerchantReceivable());
            info.setMerchantUserId(walletOrderEntity.getMerchantUserId());
            info.setOrderSourceCode(walletOrderEntity.getOrderSourceCode());
            info.setOrderType(walletOrderEntity.getOrderType());
            info.setOutTradeCode(walletOrderEntity.getOutTradeCode());
            info.setPayChannel(walletOrderEntity.getPayChannel());
            info.setPayChannelFee(walletOrderEntity.getPayChannelFee());
            info.setPayerOpenId(walletOrderEntity.getPayerOpenId());
            info.setPayerUserId(walletOrderEntity.getPayerUserId());
            info.setPayTime(walletOrderEntity.getPayTime());
            info.setPayWay(walletOrderEntity.getPayWay());
            info.setRemark(walletOrderEntity.getRemark());
            info.setScanPay(walletOrderEntity.getScanPay());
            info.setScene(walletOrderEntity.getScene());
            info.setTotalAmount(walletOrderEntity.getTotalAmount());
            info.setTotalServiceCharge(walletOrderEntity.getTotalServiceCharge());
            info.setTransactionId(walletOrderEntity.getTransactionId());
            info.setUpdateTime(walletOrderEntity.getUpdateTime());
            info.setWalletOrderCode(walletOrderEntity.getWalletOrderCode());

            // 付款详情记录
            WalletOrderDetailEntity detail = new WalletOrderDetailEntity();
            detail.setOrderId(walletOrderEntity.getId());
            detail.setAid(walletOrderEntity.getAid());
            detail.setCommissionRatio(walletOrderEntity.getCommisionRatio());
            detail.setCommissionFee(walletOrderEntity.getCommissionFee());
            detail.setCouponAmount(walletOrderEntity.getCouponAmount());
            detail.setCouponId(walletOrderEntity.getCouponId());
            detail.setCreateTime(walletOrderEntity.getCreateTime());
            detail.setUpdateTime(walletOrderEntity.getUpdateTime());
            // 商家信息填充
            CmsMerchantEntity merchant = merchantService.getById(info.getMerchantId());
            if (merchant != null) {
                detail.setProvince(merchant.getProvince());
                detail.setArea(merchant.getArea());
                detail.setCity(merchant.getCity());
                detail.setAddress(merchant.getAddress());
                info.setStoreNameAbbreviation(merchant.getStoreNameAbbreviation());
                CmsIndustryEntity industry = industryService.getById(merchant.getTradeid());
                if (industry != null) {
                    detail.setIndustryName(industry.getName());
                }
            }
            // 买家信息填充
            if (info.getPayerUserId() != null) {
                CmsMerchantUserEntity merchantUserEntity = merchantUserService.findById(info.getPayerUserId());
                if (merchantUserEntity != null) {
                    info.setPayerUserName(merchantUserEntity.getWxNickName());
                    info.setUnionId(merchantUserEntity.getUnionId());
                }
            }

            // 退款订单信息
            info.setRefundReason(orderRefundEntity.getRefundReason());
            info.setActualAmount(orderRefundEntity.getRefundAmount());
            detail.setCommissionFee(orderRefundEntity.getServiceChargeExtra());
            info.setPayChannelFee(orderRefundEntity.getServiceCharge());
            info.setTotalServiceCharge(orderRefundEntity.getServiceChargeTotal());
            info.setRemark(orderRefundEntity.getComment());
            if (orderRefundEntity.getCreateTime() != null) {
                detail.setCreateTime(orderRefundEntity.getCreateTime().getTime());
            }
            info.setHandlePerson(orderRefundEntity.getHandlePerson());
            if (orderRefundEntity.getHandleTime() != null) {
                info.setHandleTime(orderRefundEntity.getHandleTime().getTime());
            }
            info.setStatus(Integer.valueOf(orderRefundEntity.getOrderStatus()));

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("info", info);
            jsonObject.put("detail", detail);
            return jsonObject;
        }
    }
}
