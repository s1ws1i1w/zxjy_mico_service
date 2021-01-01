package com.bs.union.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:zrt
 * @Date:2020/12/26 18:05
 * @Description:com.bs.union.util.config
 * @version:1.0
 */

@Configuration
public class MinIOConfig {
    @Autowired
    private MinIOInitProperty minIOInitProperty;
    @Bean
    public MinioClient minioClient(){
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(minIOInitProperty.getRequestUrl())
                        .credentials(minIOInitProperty.getAccessKey(), minIOInitProperty.getSecretKey())
                        .build();
        return minioClient;
    }
}
