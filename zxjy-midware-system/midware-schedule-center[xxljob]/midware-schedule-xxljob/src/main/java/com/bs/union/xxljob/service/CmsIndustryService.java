package com.hdyl.schedule.xxljob.service;

import com.hdyl.schedule.xxljob.entity.pojo.CmsIndustryEntity;

public interface CmsIndustryService {
    /**
     * 根据id获取行业信息
     * @param industryId
     * @return
     */
    CmsIndustryEntity getById(Integer industryId);
}
