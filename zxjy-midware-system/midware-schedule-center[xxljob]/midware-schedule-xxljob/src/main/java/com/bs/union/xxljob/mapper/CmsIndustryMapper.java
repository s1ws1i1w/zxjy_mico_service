package com.hdyl.schedule.xxljob.mapper;

import com.hdyl.schedule.xxljob.entity.pojo.CmsIndustryEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsIndustryMapper {
    /**
     * 根据id获取行业信息
     * @param id
     * @return
     */
    CmsIndustryEntity selectById(@Param("id") Integer id);
}
