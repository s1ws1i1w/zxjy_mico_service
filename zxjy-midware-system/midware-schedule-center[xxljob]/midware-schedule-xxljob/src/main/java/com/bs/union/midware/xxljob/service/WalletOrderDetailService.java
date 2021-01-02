package com.bs.union.midware.xxljob.service;

import com.bs.union.midware.xxljob.entity.pojo.WalletOrderDetailEntity;

public interface WalletOrderDetailService {
    /**
     * 添加数据
     * @param orderDetailEntity
     * @return
     */
    int add(WalletOrderDetailEntity orderDetailEntity);
}
