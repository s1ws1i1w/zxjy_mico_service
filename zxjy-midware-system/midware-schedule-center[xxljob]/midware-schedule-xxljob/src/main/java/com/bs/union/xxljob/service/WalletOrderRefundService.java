package com.hdyl.schedule.xxljob.service;

import com.hdyl.schedule.xxljob.entity.pojo.WalletOrderEntity;
import com.hdyl.schedule.xxljob.entity.pojo.WalletOrderRefundEntity;

import java.util.List;

/**
 * 订单退款记录
 * @author guochao
 * @date 2020/9/18
 */
public interface WalletOrderRefundService {
    /**
     * 通过orderCode获取退款记录
     * @param orderCode
     * @return
     */
    WalletOrderRefundEntity getByOrderCode(String orderCode);

    /**
     * 通过id获取退款订单信息
     * @param minId
     * @param maxId
     * @return
     */
    List<WalletOrderRefundEntity> selectBetweenId(Integer minId, Integer maxId);

    /**
     * 查询最大的id
     * @return
     */
    Integer selectMaxId();

    /**
     * 根据id获取退款订单信息
     * @param orderId
     * @return
     */
    WalletOrderRefundEntity selectById(Long orderId);

    /**
     * 通过orderCode获取拒绝订单信息
     * @param walletOrderCode
     * @return
     */
    WalletOrderRefundEntity selectByOrderCode(String walletOrderCode);
}
