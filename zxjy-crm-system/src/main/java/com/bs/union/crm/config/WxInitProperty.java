package com.bs.union.crm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;


/**
 * @Author:zrt
 * @Date:2020/12/27 1:09
 * @Description:com.bs.union.crm.config
 * @version:1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "wx.open")
@RefreshScope
public class WxInitProperty {

    /**
     * 微信开放平台 appid
     */
    private String appId;
    /**
     * 微信开放平台 appsecret
     */
    private String appSecret;
    /**
     * 微信开放平台 重定向url
     */
    private String redirectUrl;
}
