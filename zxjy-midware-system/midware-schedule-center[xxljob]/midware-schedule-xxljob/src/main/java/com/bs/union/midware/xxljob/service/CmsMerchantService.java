package com.bs.union.midware.xxljob.service;

import com.bs.union.midware.xxljob.entity.pojo.CmsMerchantEntity;

public interface CmsMerchantService {
    /**
     * 根据id获取商家信息
     * @param merchantId
     * @return
     */
    CmsMerchantEntity getById(Long merchantId);
}
