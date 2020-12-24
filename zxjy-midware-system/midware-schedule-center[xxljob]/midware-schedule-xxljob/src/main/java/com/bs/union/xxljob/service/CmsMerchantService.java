package com.hdyl.schedule.xxljob.service;

import com.hdyl.schedule.xxljob.entity.pojo.CmsMerchantEntity;

public interface CmsMerchantService {
    /**
     * 根据id获取商家信息
     * @param merchantId
     * @return
     */
    CmsMerchantEntity getById(Long merchantId);
}
