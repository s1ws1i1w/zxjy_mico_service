package com.bs.union.midware.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * sys_role 实体类
 *
 * @author : zhuangruitao
 * @date : 2021/01/01 18:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel("")
public class SysRoleEntity implements Serializable {
    /**
	 * 主键id id
	 **/
    @ApiModelProperty("主键id")
    private Long id;

    /**
	 * 父级角色id parent_id
	 **/
    @ApiModelProperty("父级角色id")
    private Long parentId;

    /**
	 * 角色名称 role_name
	 **/
    @ApiModelProperty("角色名称")
    private String roleName;

    /**
	 * 角色描述 role_desc
	 **/
    @ApiModelProperty("角色描述")
    private String roleDesc;

    /**
	 * 创建操作人 create_by
	 **/
    @ApiModelProperty("创建操作人")
    private String createBy;

    /**
	 * 更新操作人 update_by
	 **/
    @ApiModelProperty("更新操作人")
    private String updateBy;

    /**
	 * 角色创建时间 create_time
	 **/
    @ApiModelProperty("角色创建时间")
    private Date createTime;

    /**
	 * 角色更新时间 update_time
	 **/
    @ApiModelProperty("角色更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
