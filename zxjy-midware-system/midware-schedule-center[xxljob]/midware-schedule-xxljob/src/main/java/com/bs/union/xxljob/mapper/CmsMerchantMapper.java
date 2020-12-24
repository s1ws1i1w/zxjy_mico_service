package com.hdyl.schedule.xxljob.mapper;

import com.hdyl.schedule.xxljob.entity.pojo.CmsMerchantEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 商家信息数据接口
 *
 * @author guochao
 * @date 2020/9/21 15:54
 */
public interface CmsMerchantMapper {
    /**
     * 根据店铺id获取店铺信息
     * @param merchantId
     * @return
     */
    CmsMerchantEntity selectById(@Param("id") Long id);
}
