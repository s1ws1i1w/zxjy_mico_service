package com.hdyl.schedule.xxljob.service.impl;

import com.hdyl.schedule.xxljob.entity.pojo.CmsMerchantEntity;
import com.hdyl.schedule.xxljob.mapper.CmsMerchantMapper;
import com.hdyl.schedule.xxljob.service.CmsMerchantService;
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
