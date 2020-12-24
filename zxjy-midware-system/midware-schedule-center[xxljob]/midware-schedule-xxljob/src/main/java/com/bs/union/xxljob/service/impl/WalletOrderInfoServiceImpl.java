package com.hdyl.schedule.xxljob.service.impl;

import com.github.pagehelper.PageHelper;
import com.hdyl.schedule.xxljob.entity.pojo.WalletOrderInfoEntity;
import com.hdyl.schedule.xxljob.mapper.WalletOrderInfoMapper;
import com.hdyl.schedule.xxljob.service.WalletOrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Godzilla
 * @create 2018-10-23 16:53
 */
@Service
@Slf4j
public class WalletOrderInfoServiceImpl implements WalletOrderInfoService {

    private final WalletOrderInfoMapper walletOrderInfoMapper;

    public WalletOrderInfoServiceImpl(WalletOrderInfoMapper walletOrderInfoMapper) {
        this.walletOrderInfoMapper = walletOrderInfoMapper;
    }

    @Override
    public int insert(WalletOrderInfoEntity walletOrderDetail) {
        return walletOrderInfoMapper.insert(walletOrderDetail);
    }

    @Override
    public List<WalletOrderInfoEntity> selectByOrderCodeList(List<String> codeList) {
        return walletOrderInfoMapper.selectByOrderCodeList(codeList);
    }

    @Override
    public List<WalletOrderInfoEntity> selectBetweenOrderId(Integer minId, Integer maxId) {
        return walletOrderInfoMapper.selectBetweenOrderId(minId, maxId);
    }

    @Override
    public void updateOrderRefund(WalletOrderInfoEntity orderDetailEntity) {
        walletOrderInfoMapper.updateOrderRefund(orderDetailEntity);
    }

    @Override
    public List<WalletOrderInfoEntity> selectListInStatus(int page, int pageSize, List<Integer> statusList) {
        PageHelper.startPage(page, pageSize);
        return walletOrderInfoMapper.selectListInStatus(statusList);
    }
}
