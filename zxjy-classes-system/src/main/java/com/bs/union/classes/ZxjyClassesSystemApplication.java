package com.bs.union.classes;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author:zrt
 * @Date:2021/1/20 23:50
 * @version:1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableCreateCacheAnnotation
@ComponentScan(basePackages = "com.bs.union")
@MapperScan("com.bs.union.classes.mapper")
public class ZxjyClassesSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZxjyClassesSystemApplication.class,args);
    }
}
