package com.hdyl.schedule.xxljob.handler;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.client.utils.StringUtils;
import com.hdyl.schedule.xxljob.XxljobApplication;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = XxljobApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
class WalletOrderTransferHandlerTest {
    @Autowired
    private WalletOrderTransferHandler walletOrderTransferHandler;

    @Test
    public void walletOrderTransferHandler() {
        walletOrderTransferHandler.walletOrderTransferHandler("{\"minId\":\"0\",\"pageSize\":\"1000\",\"batch\":\"100\"}");
    }

    @Test
    public void walletOrderCompare() {
        walletOrderTransferHandler.walletOrderCompare("{\"minId\":\"0\",\"maxId\":\"2109973\",\"batch\":\"1\"}");
    }

    @Test
    public void refundOrderCompare() {
        walletOrderTransferHandler.orderRefundCompare("{\"minId\":\"1\",\"maxId\":\"400\",\"batch\":\"1\"}");
    }

    @Test
    public void orderRefundTransfer() {
        walletOrderTransferHandler.orderRefundTransfer("{\"minId\":\"0\",\"pageSize\":\"1000\",\"batch\":\"10\"}");
    }

    @Test
    public void orderRefundUpdate() {
        walletOrderTransferHandler.orderRefundUpdate(null);
    }
}