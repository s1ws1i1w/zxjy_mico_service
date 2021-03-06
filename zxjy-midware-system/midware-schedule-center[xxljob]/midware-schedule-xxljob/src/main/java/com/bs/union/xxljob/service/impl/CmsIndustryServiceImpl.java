package com.hdyl.schedule.xxljob.service.impl;

import com.hdyl.schedule.xxljob.entity.pojo.CmsIndustryEntity;
import com.hdyl.schedule.xxljob.mapper.CmsIndustryMapper;
import com.hdyl.schedule.xxljob.service.CmsIndustryService;
import org.springframework.stereotype.Service;

/**
 * 行业信息接口实现
 *
 * @author guochao
 * @date 2020/9/21 16:00
 */
@Service
public class CmsIndustryServiceImpl implements CmsIndustryService {
    private final CmsIndustryMapper industryMapper;

    public CmsIndustryServiceImpl(CmsIndustryMapper industryMapper) {
        this.industryMapper = industryMapper;
    }

    @Override
    public CmsIndustryEntity getById(Integer industryId) {
        return industryMapper.selectById(industryId);
    }
}
