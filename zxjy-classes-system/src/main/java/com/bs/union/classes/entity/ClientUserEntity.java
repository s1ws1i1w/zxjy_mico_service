package com.bs.union.classes.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * client_user 实体类
 *
 * @author : zhouyibin   gralves@163.com
 * @date : 2021/01/21 00:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel("")
public class ClientUserEntity implements Serializable {
    /**
	 * 主键id id
	 **/
    @ApiModelProperty("主键id")
    private Long id;

    /**
	 * 微信昵称 wx_name
	 **/
    @ApiModelProperty("微信昵称")
    private String wxName;

    /**
	 * 微信头像 wx_avatar
	 **/
    @ApiModelProperty("微信头像")
    private String wxAvatar;

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
