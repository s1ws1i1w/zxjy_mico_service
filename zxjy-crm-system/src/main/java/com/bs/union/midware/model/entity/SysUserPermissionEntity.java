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
 * sys_user_permission 实体类
 *
 * @author : zhuangruitao
 * @date : 2021/01/01 18:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel("")
public class SysUserPermissionEntity implements Serializable {
    /**
	 * id id
	 **/
    @ApiModelProperty("id")
    private Long id;

    /**
	 * 系统用户id sys_user_id
	 **/
    @ApiModelProperty("系统用户id")
    private Long sysUserId;

    /**
	 * 系统权限id sys_permission_id
	 **/
    @ApiModelProperty("系统权限id")
    private Long sysPermissionId;

    /**
	 * 创建时间 create_time
	 **/
    @ApiModelProperty("创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;
}
