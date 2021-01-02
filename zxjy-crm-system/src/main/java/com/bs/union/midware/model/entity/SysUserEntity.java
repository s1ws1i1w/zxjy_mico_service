package com.bs.union.midware.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * sys_user 实体类
 *
 * @author : zhuangruitao
 * @date : 2021/01/01 18:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel("")
public class SysUserEntity implements Serializable, UserDetails {
    /**
	 * 主键id id
	 **/
    @ApiModelProperty("主键id")
    private Long id;

    /**
	 * 昵称 nick_name
	 **/
    @ApiModelProperty("昵称")
    private String nickName;

    /**
	 * 用户名 user_name
	 **/
    @ApiModelProperty("用户名")
    private String userName;

    /**
	 * 0:女1:男 gender
	 **/
    @ApiModelProperty("0:女1:男")
    private String gender;

    /**
	 * 入职时间 hire_time
	 **/
    @ApiModelProperty("入职时间")
    private Date hireTime;

    /**
	 * 登录账号 account
	 **/
    @ApiModelProperty("登录账号")
    private String account;

    /**
	 * 头像 avatar
	 **/
    @ApiModelProperty("头像")
    private String avatar;

    /**
	 * 登录密码, password
	 **/
    @ApiModelProperty("登录密码,")
    private String password;

    /**
	 * 盐值 salt
	 **/
    @ApiModelProperty("盐值")
    private String salt;

    /**
	 * ipv4 login_ip
	 **/
    @ApiModelProperty("ipv4")
    private String loginIp;

    /**
	 * 部门id dept_id
	 **/
    @ApiModelProperty("部门id")
    private Long deptId;

    /**
	 * 部门名称 dept_name
	 **/
    @ApiModelProperty("部门名称")
    private String deptName;

    /**
	 * 角色id role_id
	 **/
    @ApiModelProperty("角色id")
    private Long roleId;

    /**
	 * 角色名称 role_name
	 **/
    @ApiModelProperty("角色名称")
    private String roleName;

    /**
	 * 职位名称 position_name
	 **/
    @ApiModelProperty("职位名称")
    private String positionName;

    /**
	 * 备注 comment
	 **/
    @ApiModelProperty("备注")
    private String comment;

    /**
	 * 生日 birthday
	 **/
    @ApiModelProperty("生日")
    private Date birthday;

    /**
	 * 邮箱 email
	 **/
    @ApiModelProperty("邮箱")
    private String email;

    /**
	 * 身份证编号 id_no
	 **/
    @ApiModelProperty("身份证编号")
    private String idNo;

    /**
	 * 微信号 wx
	 **/
    @ApiModelProperty("微信号")
    private String wx;

    /**
	 * 0:禁用,1:启用 status
	 **/
    @ApiModelProperty("0:禁用,1:启用")
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
	 * 角色创建时间 create_time
	 **/
    @ApiModelProperty("角色创建时间")
    private Date createTime;

    private Date updateTime;

    private List<SysRoleEntity> roles;

    private static final long serialVersionUID = 1L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        for (SysRoleEntity role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
