package com.hdyl.schedule.xxljob.mapper;

import com.hdyl.schedule.xxljob.entity.pojo.WalletOrderDetailEntity;
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
