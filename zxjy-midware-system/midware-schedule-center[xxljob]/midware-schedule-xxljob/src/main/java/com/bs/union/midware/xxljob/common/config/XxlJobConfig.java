package com.bs.union.midware.xxljob.common.config;

import com.google.common.collect.Lists;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;

/**
 * xxl-job com.bs.union.config
 */
@Configuration
public class XxlJobConfig {
    private Logger logger = LoggerFactory.getLogger(XxlJobConfig.class);

    @Value("${xxl.job.admin.addresses:hrz-schedule-xxl-admin}")
    private String adminAddresses;

    @Value("${xxl.job.executor.appname}")
    private String appName;

    @Value("${xxl.job.executor.ip}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private String port;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Value("${xxl.job.executor.logpath}")
    private String logPath;

    @Value("${xxl.job.executor.logretentiondays}")
    private int logRetentionDays;

    @Resource
    private DiscoveryClient discoveryClient;

    @ConditionalOnClass(value = DiscoveryClient.class)
    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobSpringExecutor xxlJobExecutor() {
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAppName(appName);
        // 根据spring cloud注册中心获取对应xxl-amdmin服务列表进行注册
        if (StringUtils.isNotBlank(adminAddresses)) {
            List<ServiceInstance> instances = discoveryClient.getInstances(adminAddresses);
            if (!instances.isEmpty()) {
                List<String> addressList = Lists.newArrayList();
                for (ServiceInstance instance : instances) {
                    addressList.add("http://" + instance.getHost() + ":" + instance.getPort());
                }
                xxlJobSpringExecutor.setAdminAddresses(StringUtils.join(addressList,","));
            }
        }
        if (StringUtils.isNotBlank(ip)) {
            xxlJobSpringExecutor.setIp(ip);
        }
        if (StringUtils.isNotBlank(port)) {
            xxlJobSpringExecutor.setPort(Integer.parseInt(port));
        }
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);

        return xxlJobSpringExecutor;
    }

}
