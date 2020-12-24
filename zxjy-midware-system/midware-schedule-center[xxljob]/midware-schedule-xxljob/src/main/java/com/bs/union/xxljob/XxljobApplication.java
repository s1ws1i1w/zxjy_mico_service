package com.bs.union.xxljob;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableFeignClients
@SpringCloudApplication
@ComponentScan(basePackages = "com.bs.union")
public class XxljobApplication {
    public static void main(String[] args) {
        SpringApplication.run(XxljobApplication.class, args);
    }

}
