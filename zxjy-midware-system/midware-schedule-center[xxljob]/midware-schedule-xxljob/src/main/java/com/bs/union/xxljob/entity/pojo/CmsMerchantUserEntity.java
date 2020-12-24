package com.hdyl.schedule.xxljob.entity.pojo;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 用户实体类
 *
 * @author tt
 * @date 2020年06月05日
 */
@Data
@ToString
@Accessors(chain = true)
public class CmsMerchantUserEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 微信昵称
     */
    private String wxNickName;

    /**
     * 微信unionId
     */
    private String unionId;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 享多赚小程序openId
     */
    private String openId;

    /**
     * 头像url
     */
    private String avatarUrl;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 享少花微信openId
     */
    private String xshOpenId;

    /**
     * e省宝APP openid
     */
    private String esbAppOpenId;

    /**
     * e省宝APP openid
     */
    private String esbMpOpenId;

    /**
     * 手机号码验证状态
     */
    private Integer phoneValidate;

    /**
     * 账户id
     */
    private Long accountId;

    /**
     * 享多赚服务号openid
     */
    private String xdzServerOpenId;

    /**
     * 享少花服务号openid
     */
    private String xshServerOpenId;

    /**
     * 享多赚客户端 openid
     */
    private String xdzAppOpenId;

    /**
     * mediaId
     */
    private Long mediaId;

    /**
     * 享多联盟服务号openid
     */
    private String xdlmServerOpenId;

    /**
     * 是否删除,0:未注销,1:已注销,2:待注销
     */
    private String isDelete;

    /**
     * 默认店铺的accountId
     */
    private Long pushMerchantAccountId;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * sessionId
     */
    private String sessionId;

    /**
     * token
     */
    private String token;
}