package com.bs.union.midware.xxljob.service;

import com.bs.union.midware.xxljob.entity.pojo.CmsIndustryEntity;

public interface CmsIndustryService {
    /**
     * 根据id获取行业信息
     * @param industryId
     * @return
     */
    CmsIndustryEntity getById(Integer industryId);
}
