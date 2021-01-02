package com.bs.union.midware.xxljob.mapper;

import com.bs.union.midware.xxljob.entity.pojo.WalletOrderRefundEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletOrderRefundMapper {
    /**
     * 通过orderCode获取退款记录
     * @param orderCode
     * @return
     */
    WalletOrderRefundEntity selectByOrderCode(@Param("orderCode") String orderCode);

    /**
     * 通过id获取退款订单
     * @param minId
     * @param maxId
     * @return
     */
    List<WalletOrderRefundEntity> selectBetweenId(@Param("minId") Integer minId, @Param("maxId") Integer maxId);

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
    WalletOrderRefundEntity selectById(@Param("orderId") Long orderId);
}
