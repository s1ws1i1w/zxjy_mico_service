package com.bs.union.crm.mapper.user;

import com.bs.union.model.base.deprecated.BaseMapper;
import com.bs.union.model.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * sys_role 持久化层
 *
 * @author : zhuangruitao  1335877357@qq.com
 * @date : 2021/01/01 18:12
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRoleEntity, Long> {
}
