package com.bs.union.midware.xxljob.mapper;

import com.bs.union.midware.xxljob.entity.pojo.WalletOrderDetailEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletOrderDetailMapper {
    /**
     * 添加记录
     *
     * @param orderDetailEntity
     * @return
     */
    int insert(WalletOrderDetailEntity orderDetailEntity);
}
