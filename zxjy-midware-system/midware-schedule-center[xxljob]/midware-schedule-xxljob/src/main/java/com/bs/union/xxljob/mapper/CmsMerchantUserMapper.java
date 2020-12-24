package com.hdyl.schedule.xxljob.mapper;

import com.hdyl.schedule.xxljob.entity.pojo.CmsMerchantUserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * cms_merchant_user 持久化层
 *
 * @author : zhouyibin  gralves@163.com
 * @date : 2020/08/31 22:54
 */
@Repository
public interface CmsMerchantUserMapper {
    /**
     * 用户id查询用户昵称
     *
     * @param userId 用户id
     * @return 用户昵称
     * @author gralves
     * @date 2020-08-31
     */
    CmsMerchantUserEntity findById(@Param("userId") Long userId);
}