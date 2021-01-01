package com.bs.union.crm;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.bs.union.crm.config.WxInitProperty;
import com.bs.union.util.OssStoreFileUtil;
import io.minio.MinioClient;
import io.minio.messages.Bucket;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 *
 * @Author:zrt
 * @Date:2020/12/26  18:49
 * @Description:com.bs.union.crm
 * @version:1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {
    @Autowired
    private WxInitProperty wxInitProperty;

    @org.junit.Test
    public void test() {
    }
}
