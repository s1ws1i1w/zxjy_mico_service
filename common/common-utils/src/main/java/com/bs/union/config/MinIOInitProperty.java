package com.bs.union.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author:zrt
 * @Date:2020/12/27  0:02
 * @Description:com.bs.union.config
 * @version:1.0
 */
@Component
@ConfigurationProperties(prefix = "minio.config")
@Data
public class MinIOInitProperty {
    private String accessKey;
    private String secretKey;
    private String requestUrl;
}
