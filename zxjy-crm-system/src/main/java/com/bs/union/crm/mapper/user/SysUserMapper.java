package com.bs.union.crm.mapper.user;


import com.bs.union.crm.model.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;


/**
 * sys_user 持久化层
 *
 * @author : zhuangruitao  1335877357@qq.com
 * @date : 2021/01/01 18:00
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {
}
