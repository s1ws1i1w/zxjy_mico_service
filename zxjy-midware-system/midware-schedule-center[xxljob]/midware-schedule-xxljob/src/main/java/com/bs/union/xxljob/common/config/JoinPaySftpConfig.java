package com.hdyl.schedule.xxljob.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 汇聚 SFTP  配置
 *
 * @author 齐高新
 * @date 2020年9月21日
 */
@Data
@Configuration
public class JoinPaySftpConfig {

    @Value("${joinpay.sftp.username:888106600007749}")
    private String username;

    @Value("${joinpay.sftp.password:SFbROEp0koK1N3}")
    private String password;

    @Value("${joinpay.sftp.host:120.79.72.172}")
    private String host;

    @Value("${joinpay.sftp.port:8000}")
    private Integer port;

}
