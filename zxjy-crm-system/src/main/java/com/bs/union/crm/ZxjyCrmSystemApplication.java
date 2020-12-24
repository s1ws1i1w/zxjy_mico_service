package com.bs.union.crm;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author:zrt
 * @Date:2020/12/18  23:21
 * @Description:com.bs.union.crm
 * @version:1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableCreateCacheAnnotation
@ComponentScan(basePackages = "com.bs.union")
public class ZxjyCrmSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZxjyCrmSystemApplication.class,args);
    }
}
