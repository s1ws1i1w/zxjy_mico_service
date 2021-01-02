package com.bs.union.midware.xxljob.service.impl;

import com.bs.union.midware.xxljob.entity.pojo.CmsMerchantUserEntity;
import com.bs.union.midware.xxljob.mapper.CmsMerchantUserMapper;
import com.bs.union.midware.xxljob.service.CmsMerchantUserService;
import org.springframework.stereotype.Service;

/**
 * @author guochao
 * @date 2020/9/23 14:53
 */
@Service
public class CmsMerchantUserServiceImpl implements CmsMerchantUserService {
    private final CmsMerchantUserMapper merchantUserMapper;

    public CmsMerchantUserServiceImpl(CmsMerchantUserMapper merchantUserMapper) {
        this.merchantUserMapper = merchantUserMapper;
    }

    @Override
    public CmsMerchantUserEntity findById(Long userId) {
        return merchantUserMapper.findById(userId);
    }
}
