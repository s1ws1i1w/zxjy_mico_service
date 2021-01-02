package com.bs.union.midware.xxljob.service.impl;

import com.bs.union.midware.xxljob.entity.pojo.WalletOrderEntity;
import com.bs.union.midware.xxljob.mapper.WalletOrderMapper;
import com.bs.union.midware.xxljob.service.WalletOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Godzilla
 * @create 2018-10-23 16:53
 */
@Service
@Slf4j
public class WalletOrderServiceImpl implements WalletOrderService {

    private final WalletOrderMapper walletOrderMapper;

    public WalletOrderServiceImpl(WalletOrderMapper walletOrderMapper) {
        this.walletOrderMapper = walletOrderMapper;
    }

    @Override
    public Integer selectMaxId() {
        return walletOrderMapper.selectMaxId();
    }

    @Override
    public List<WalletOrderEntity> selectBetweenId(int minId, int maxId) {
        return walletOrderMapper.selectBetweenId(minId, maxId);
    }

    @Override
    public WalletOrderEntity selectByWalletOrderCode(String walletOrderCode) {
        return walletOrderMapper.selectByWalletOrderCode(walletOrderCode);
    }
}
