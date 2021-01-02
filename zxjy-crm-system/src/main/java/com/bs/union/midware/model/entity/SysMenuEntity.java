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
 * sys_menu 实体类
 *
 * @author : zhouyibin   gralves@163.com
 * @date : 2021/01/01 18:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel("")
public class SysMenuEntity implements Serializable {
    /**
	 * 主键id id
	 **/
    @ApiModelProperty("主键id")
    private Long id;

    /**
	 * 父级菜单id parent_id
	 **/
    @ApiModelProperty("父级菜单id")
    private Long parentId;

    /**
	 * 排序 sort
	 **/
    @ApiModelProperty("排序")
    private Integer sort;

    /**
	 * 权限字符串 permission
	 **/
    @ApiModelProperty("权限字符串")
    private String permission;

    /**
	 * 权限描述 permission_desc
	 **/
    @ApiModelProperty("权限描述")
    private String permissionDesc;

    /**
	 * 图标链接 icon
	 **/
    @ApiModelProperty("图标链接")
    private String icon;

    /**
	 * 菜单名称 menu_name
	 **/
    @ApiModelProperty("菜单名称")
    private String menuName;

    /**
	 * 请求路径 req_path
	 **/
    @ApiModelProperty("请求路径")
    private String reqPath;

    /**
	 * 本地跳转路径 local_path
	 **/
    @ApiModelProperty("本地跳转路径")
    private String localPath;

    /**
	 * 菜单描述 menu_desc
	 **/
    @ApiModelProperty("菜单描述")
    private String menuDesc;

    /**
	 * 菜单类型,0:目录,1:菜单,2:按钮 type
	 **/
    @ApiModelProperty("菜单类型,0:目录,1:菜单,2:按钮")
    private String type;

    /**
	 * 状态,0:弃用,1:启用 status
	 **/
    @ApiModelProperty("状态,0:弃用,1:启用")
    private String status;

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
	 * 0: 当前窗口,1:新窗口,2:跳出浏览器,打开新窗口 target_window
	 **/
    @ApiModelProperty("0: 当前窗口,1:新窗口,2:跳出浏览器,打开新窗口")
    private String targetWindow;

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
