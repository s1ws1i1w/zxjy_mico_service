package com.hdyl.schedule.xxljob.mapper;

import com.hdyl.schedule.xxljob.entity.pojo.WalletOrderEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletOrderMapper {
    /**
     * 查询最大的id
     * @return
     */
    Integer selectMaxId();

    /**
     * 根据id获取支付订单
     * @param minId
     * @param maxId
     * @return
     */
    List<WalletOrderEntity> selectBetweenId(@Param("minId") int minId, @Param("maxId") int maxId);

    /**
     * 通过内部订单号获取
     * @param walletOrderCode
     * @return
     */
    WalletOrderEntity selectByWalletOrderCode(@Param("orderCode") String walletOrderCode);

}
