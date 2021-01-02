package com.bs.union.midware.xxljob.service.impl;

import com.bs.union.midware.xxljob.entity.pojo.WalletOrderDetailEntity;
import com.bs.union.midware.xxljob.mapper.WalletOrderDetailMapper;
import com.bs.union.midware.xxljob.service.WalletOrderDetailService;
import org.springframework.stereotype.Service;

/**
 * 订单详情信息
 *
 * @author guochao
 * @date 2020/9/22 11:23
 */
@Service
public class WalletOrderDetailServiceImpl implements WalletOrderDetailService {
    private final WalletOrderDetailMapper orderDetailMapper;

    public WalletOrderDetailServiceImpl(WalletOrderDetailMapper orderDetailMapper) {
        this.orderDetailMapper = orderDetailMapper;
    }

    @Override
    public int add(WalletOrderDetailEntity orderDetailEntity) {
        return orderDetailMapper.insert(orderDetailEntity);
    }
}
