package com.bs.union.midware.xxljob.service.impl;

import com.bs.union.midware.xxljob.entity.pojo.CmsMerchantEntity;
import com.bs.union.midware.xxljob.mapper.CmsMerchantMapper;
import com.bs.union.midware.xxljob.service.CmsMerchantService;
import org.springframework.stereotype.Service;

/**
 * 商家信息接口实现
 *
 * @author guochao
 * @date 2020/9/21 16:00
 */
@Service
public class CmsMerchantServiceImpl implements CmsMerchantService {
    private final CmsMerchantMapper cmsMerchantMapper;

    public CmsMerchantServiceImpl(CmsMerchantMapper cmsMerchantMapper) {
        this.cmsMerchantMapper = cmsMerchantMapper;
    }

    @Override
    public CmsMerchantEntity getById(Long merchantId) {
        return cmsMerchantMapper.selectById(merchantId);
    }
}
