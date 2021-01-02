package com.bs.union.midware.xxljob.service;

import com.bs.union.midware.xxljob.entity.pojo.CmsMerchantUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * cms_merchant_user 持久化层
 *
 * @author : zhouyibin  gralves@163.com
 * @date : 2020/08/31 22:54
 */
@Mapper
public interface CmsMerchantUserService {
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
