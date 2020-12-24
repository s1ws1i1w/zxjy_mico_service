package com.hdyl.schedule.xxljob.service;

import com.hdyl.schedule.xxljob.entity.pojo.WalletOrderInfoEntity;

import java.util.List;

/**
 * 新的聚合订单记录
 * @author guochao
 * @date 2020/9/18
 */
public interface WalletOrderInfoService {
    /**
     * 添加记录
     * @param walletOrderDetail
     */
    int insert(WalletOrderInfoEntity walletOrderDetail);

    /**
     * 通过内部订单号获取支付订单
     * @param codeList
     * @return
     */
    List<WalletOrderInfoEntity> selectByOrderCodeList(List<String> codeList);

    /**
     * 通过orderId获取支付订单
     * @param minId
     * @param maxId
     * @return
     */
    List<WalletOrderInfoEntity> selectBetweenOrderId(Integer minId, Integer maxId);

    /**
     * 修改退款订单信息
     * @param orderDetailEntity
     */
    void updateOrderRefund(WalletOrderInfoEntity orderDetailEntity);

    /**
     * 根据订单状态获取
     * @param page
     * @param pageSize
     * @param statusList
     * @return
     */
    List<WalletOrderInfoEntity> selectListInStatus(int page, int pageSize, List<Integer> statusList);
}
