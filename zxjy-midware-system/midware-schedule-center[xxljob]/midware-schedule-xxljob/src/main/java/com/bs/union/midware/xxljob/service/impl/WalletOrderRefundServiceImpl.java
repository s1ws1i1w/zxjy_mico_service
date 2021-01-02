package com.bs.union.midware.xxljob.service.impl;

import com.bs.union.midware.xxljob.entity.pojo.WalletOrderRefundEntity;
import com.bs.union.midware.xxljob.mapper.WalletOrderRefundMapper;
import com.bs.union.midware.xxljob.service.WalletOrderRefundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 退款订单记录
 * @author guochao
 * @date 2020/9/18
 */
@Service
@Slf4j
public class WalletOrderRefundServiceImpl implements WalletOrderRefundService {

    private final WalletOrderRefundMapper walletOrderRefundMapper;

    public WalletOrderRefundServiceImpl(WalletOrderRefundMapper walletOrderRefundMapper) {
        this.walletOrderRefundMapper = walletOrderRefundMapper;
    }

    @Override
    public WalletOrderRefundEntity getByOrderCode(String orderCode) {
        return walletOrderRefundMapper.selectByOrderCode(orderCode);
    }

    @Override
    public List<WalletOrderRefundEntity> selectBetweenId(Integer minId, Integer maxId) {
        return walletOrderRefundMapper.selectBetweenId(minId, maxId);
    }

    @Override
    public Integer selectMaxId() {
        return walletOrderRefundMapper.selectMaxId();
    }

    @Override
    public WalletOrderRefundEntity selectById(Long orderId) {
        return walletOrderRefundMapper.selectById(orderId);
    }

    @Override
    public WalletOrderRefundEntity selectByOrderCode(String walletOrderCode) {
        return walletOrderRefundMapper.selectByOrderCode(walletOrderCode);
    }
}
