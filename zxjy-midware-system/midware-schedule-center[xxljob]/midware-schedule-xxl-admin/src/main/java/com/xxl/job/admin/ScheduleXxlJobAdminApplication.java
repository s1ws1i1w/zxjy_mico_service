package com.xxl.job.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xuxueli 2018-10-28 00:38:13
 */
//@SpringBootApplication
@EnableFeignClients
@SpringCloudApplication
public class ScheduleXxlJobAdminApplication {

	public static void main(String[] args) {
        SpringApplication.run(ScheduleXxlJobAdminApplication.class, args);
	}

}