package com.bs.union.midware.xxljob.manager;

/**
 * 支付订单对比
 * @author guochao
 * @date 2020/9/18
 */
public interface WalletOrderRefundCompareManagerService {
    /**
     * 支付订单数据迁移对比
     * @param minId
     * @param maxId
     * @param batch
     */
    void walletOrderRefundCompare(Integer minId, Integer maxId, Integer batch);
}
