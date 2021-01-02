package com.bs.union.midware.xxljob.service;

import com.bs.union.midware.xxljob.entity.pojo.WalletOrderEntity;

import java.util.List;

/**
 * 订单记录
 * @author guochao
 * @date 2020/9/18
 */
public interface WalletOrderService {
    /**
     * 查询最大的id
     * @return
     */
    Integer selectMaxId();

    /**
     * 根据id区间
     * @param minId
     * @param maxId
     * @return
     */
    List<WalletOrderEntity> selectBetweenId(int minId, int maxId);

    /**
     * 通过内部订单号获取订单信息
     * @param walletOrderCode
     * @return
     */
    WalletOrderEntity selectByWalletOrderCode(String walletOrderCode);
}
