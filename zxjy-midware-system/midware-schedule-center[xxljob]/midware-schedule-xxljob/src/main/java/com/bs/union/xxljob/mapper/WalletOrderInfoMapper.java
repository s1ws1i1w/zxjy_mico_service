package com.hdyl.schedule.xxljob.mapper;

import com.hdyl.schedule.xxljob.entity.pojo.WalletOrderEntity;
import com.hdyl.schedule.xxljob.entity.pojo.WalletOrderInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletOrderInfoMapper {
    /**
     * 批量添加
     * @param walletOrderDetail
     */
    int insert(WalletOrderInfoEntity walletOrderDetail);

    /**
     * 通过内部订单号获取订单信息
     * @param codeList
     * @return
     */
    List<WalletOrderInfoEntity> selectByOrderCodeList(@Param("codeList") List<String> codeList);

    /**
     * 通过orderId获取订单信息
     * @param minId
     * @param maxId
     * @return
     */
    List<WalletOrderInfoEntity> selectBetweenOrderId(@Param("minId") Integer minId, @Param("maxId") Integer maxId);

    /**
     * 修改退款订单信息
     * @param orderDetailEntity
     */
    void updateOrderRefund(WalletOrderInfoEntity orderDetailEntity);

    /**
     * 获取指定状态订单信息
     * @param statusList
     * @return
     */
    List<WalletOrderInfoEntity> selectListInStatus(@Param("statusList") List<Integer> statusList);

    /**
     * 根据订单号查找对应订单
     *
     * @param walletOrderCode 订单号
     * @return 订单
     * @author qigaoxin
     */
    WalletOrderInfoEntity selectOrderByWalletOrderCode(@Param("walletOrderCode") String walletOrderCode);
}
