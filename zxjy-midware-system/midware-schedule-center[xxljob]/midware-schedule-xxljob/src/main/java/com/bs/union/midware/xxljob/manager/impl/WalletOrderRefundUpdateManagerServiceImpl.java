package com.bs.union.midware.xxljob.manager.impl;

import com.bs.union.midware.xxljob.entity.pojo.WalletOrderInfoEntity;
import com.bs.union.midware.xxljob.entity.pojo.WalletOrderRefundEntity;
import com.hdyl.merchant.union.common.utils.DingTalkUtil;
import com.bs.union.midware.xxljob.manager.WalletOrderRefundUpdateManagerService;
import com.bs.union.midware.xxljob.service.WalletOrderInfoService;
import com.bs.union.midware.xxljob.service.WalletOrderRefundService;
import com.bs.union.midware.xxljob.service.WalletOrderService;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author Godzilla
 * @create 2018-10-23 16:53
 */
@Service
@Slf4j
public class WalletOrderRefundUpdateManagerServiceImpl implements WalletOrderRefundUpdateManagerService {

    private final WalletOrderService walletOrderService;
    private final WalletOrderInfoService walletOrderInfoService;
    private final WalletOrderRefundService walletOrderRefundService;
    private final StringRedisTemplate redisTemplate;

    public WalletOrderRefundUpdateManagerServiceImpl(WalletOrderService walletOrderService, WalletOrderInfoService walletOrderInfoService, WalletOrderRefundService walletOrderRefundService, StringRedisTemplate redisTemplate) {
        this.walletOrderService = walletOrderService;
        this.walletOrderInfoService = walletOrderInfoService;
        this.walletOrderRefundService = walletOrderRefundService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void walletOrderRefundUpdate() {
        int pageSize = 100;
        int page = 1;
        List<Integer> statusList = Arrays.asList(5,7);
        while (true) {
            List<WalletOrderInfoEntity> orderDetailList = walletOrderInfoService.selectListInStatus(page, pageSize, statusList);
            if (orderDetailList.size() > 0) {
                for (WalletOrderInfoEntity orderDetailEntity : orderDetailList) {
                    WalletOrderRefundEntity orderRefundEntity = walletOrderRefundService.selectByOrderCode(orderDetailEntity.getWalletOrderCode());
                    if (orderRefundEntity == null ) {
                        XxlJobLogger.log("没有查询到退款订单，orderCode:{}", orderDetailEntity.getWalletOrderCode());
                        DingTalkUtil.sendTextMessage("没有查询到退款订单，orderCode:" + orderDetailEntity.getWalletOrderCode(), DingTalkUtil.SHENGBAO_ERROR_YABA);
                    } else {
                        if (StringUtils.isBlank(orderRefundEntity.getOrderStatus())) {
                            XxlJobLogger.log("退款订单没有状态值：" + orderRefundEntity.getId());
                            DingTalkUtil.sendTextMessage("退款订单没有状态值，orderCode:" + orderDetailEntity.getWalletOrderCode(), DingTalkUtil.SHENGBAO_ERROR_YABA);
                        } else {
                            if (!orderDetailEntity.getStatus().equals(Integer.valueOf(orderRefundEntity.getOrderStatus()))) {
                                orderDetailEntity.setStatus(Integer.valueOf(orderRefundEntity.getOrderStatus()));
                                orderDetailEntity.setRefundReason(orderRefundEntity.getRefundReason());
                                orderDetailEntity.setRemark(orderRefundEntity.getComment());
                                orderDetailEntity.setHandlePerson(orderRefundEntity.getHandlePerson());
                                if (orderRefundEntity.getHandleTime() != null) {
                                    orderDetailEntity.setHandleTime(orderRefundEntity.getHandleTime().getTime());
                                }
                                walletOrderInfoService.updateOrderRefund(orderDetailEntity);
                            }
                        }
                    }
                }
            }
            if (orderDetailList.size() < pageSize) {
                break;
            }
        }
    }
}
