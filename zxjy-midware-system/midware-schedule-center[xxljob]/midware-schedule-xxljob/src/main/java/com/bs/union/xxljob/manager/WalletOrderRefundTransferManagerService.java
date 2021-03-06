package com.hdyl.schedule.xxljob.manager;

/**
 * 支付订单迁移
 * @author guochao
 * @date 2020/9/18
 */
public interface WalletOrderRefundTransferManagerService {
    /**
     * 支付订单数据迁移
     * @param minId
     * @param batch
     */
    void walletOrderRefundTransfer(Integer minId, Integer pageSize, Integer batch);
}
