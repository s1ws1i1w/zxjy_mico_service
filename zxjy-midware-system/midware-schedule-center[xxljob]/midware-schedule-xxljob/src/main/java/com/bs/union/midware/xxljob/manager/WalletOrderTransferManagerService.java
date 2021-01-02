package com.bs.union.midware.xxljob.manager;

/**
 * 支付订单迁移
 * @author guochao
 * @date 2020/9/18
 */
public interface WalletOrderTransferManagerService {
    /**
     * 支付订单数据迁移
     * @param minId
     * @param batch
     */
    void walletOrderTransfer(Integer minId, Integer pageSize, Integer batch);
}
