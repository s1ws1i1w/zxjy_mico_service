package com.bs.union.midware;


import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@EnableDiscoveryClient
@SpringBootApplication
@EnableCreateCacheAnnotation
@ComponentScan(basePackages = "com.bs.union")
@MapperScan(basePackages = "com.bs.union.midware.mapper")
@EnableAspectJAutoProxy
public class MidwareAuthenticationApplication {
    public static void main(String[] args) {
        SpringApplication.run(MidwareAuthenticationApplication.class,args);
    }
}
