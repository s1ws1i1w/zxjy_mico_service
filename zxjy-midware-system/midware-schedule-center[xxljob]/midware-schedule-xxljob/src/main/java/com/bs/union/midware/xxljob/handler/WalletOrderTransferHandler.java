package com.bs.union.midware.xxljob.handler;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.client.utils.StringUtils;
import com.hdyl.schedule.xxljob.manager.*;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: xulangbing
 * @Date: 2020/6/22 0022 13:59
 * @describe: 精选商品定时任务
 */
@Slf4j
@Component
public class WalletOrderTransferHandler {
    private final WalletOrderTransferManagerService walletOrderTransferManagerService;
    private final WalletOrderCompareManagerService walletOrderCompareManagerService;
    private final WalletOrderRefundTransferManagerService walletOrderRefundTransferManagerService;
    private final WalletOrderRefundCompareManagerService walletOrderRefundCompareManagerService;
    private final WalletOrderRefundUpdateManagerService walletOrderRefundUpdateManagerService;

    public WalletOrderTransferHandler(WalletOrderTransferManagerService walletOrderTransferManagerService, WalletOrderCompareManagerService walletOrderCompareManagerService, WalletOrderRefundTransferManagerService walletOrderRefundTransferManagerService, WalletOrderRefundCompareManagerService walletOrderRefundCompareManagerService, WalletOrderRefundUpdateManagerService walletOrderRefundUpdateManagerService) {
        this.walletOrderTransferManagerService = walletOrderTransferManagerService;
        this.walletOrderCompareManagerService = walletOrderCompareManagerService;
        this.walletOrderRefundTransferManagerService = walletOrderRefundTransferManagerService;
        this.walletOrderRefundCompareManagerService = walletOrderRefundCompareManagerService;
        this.walletOrderRefundUpdateManagerService = walletOrderRefundUpdateManagerService;
    }

    /**
     * 迁移支付订单记录
     * @param params
     * {"minId":"-2","pageSize“：”1000“}
     * 为-2则是从上次拉单的id位置开始拉单，第一次拉则从0开始拉
     * @return
     */
    @XxlJob("walletOrderTransfer")
    public ReturnT<String> walletOrderTransferHandler(String params) {
        Integer minId = -2;
        Integer pageSize = 1000;
        Integer batch = 10;
        if (StringUtils.isNotBlank(params)){
            JSONObject jsonObject = JSONObject.parseObject(params);
            minId = jsonObject.getInteger("minId") - 1;
            pageSize = jsonObject.getInteger("pageSize");
            batch = jsonObject.getInteger("batch");
        }
        walletOrderTransferManagerService.walletOrderTransfer(minId, pageSize, batch);
        return ReturnT.SUCCESS;
    }

    /**
     * 迁移退款订单记录
     * @param params
     * {"minId":"-1","pageSize“：”1000“}
     * 为-1则是从上次拉单的id位置开始拉单，第一次拉则从0开始拉
     * @return
     */
    @XxlJob("orderRefundTransfer")
    public ReturnT<String> orderRefundTransfer(String params) {
        Integer minId = -1;
        Integer pageSize = 1000;
        Integer batch = 1;
        if (StringUtils.isNotBlank(params)){
            JSONObject jsonObject = JSONObject.parseObject(params);
            minId = jsonObject.getInteger("minId");
            pageSize = jsonObject.getInteger("pageSize");
            batch = jsonObject.getInteger("batch");
        }
        walletOrderRefundTransferManagerService.walletOrderRefundTransfer(minId, pageSize, batch);
        return ReturnT.SUCCESS;
    }

    /**
     * 退款订单状态更新
     * @param params
     * {"minId":"-1","pageSize“：”1000“}
     * 为-1则是从上次拉单的id位置开始拉单，第一次拉则从0开始拉
     * @return
     */
    @XxlJob("orderRefundUpdate")
    public ReturnT<String> orderRefundUpdate(String params) {
        walletOrderRefundUpdateManagerService.walletOrderRefundUpdate();
        return ReturnT.SUCCESS;
    }

    /**
     * 迁移支付订单对比
     * @param params
     * {"minId":"-2","maxId“：”1000“}
     * 为-2则是从上次拉单的id位置开始拉单，第一次拉则从0开始拉
     * @return
     */
    @XxlJob("walletOrderCompare")
    public ReturnT<String> walletOrderCompare(String params) {
        Integer minId = -2;
        Integer maxId = null;
        Integer batch = 10;
        if (StringUtils.isNotBlank(params)){
            JSONObject jsonObject = JSONObject.parseObject(params);
            minId = jsonObject.getInteger("minId") - 1;
            batch = jsonObject.getInteger("batch");
            maxId = jsonObject.get("maxId") != null ? jsonObject.getInteger("maxId") : null;
        }
        walletOrderCompareManagerService.walletOrderCompare(minId, maxId, batch);
        return ReturnT.SUCCESS;
    }

    /**
     * 迁移退款订单对比
     * @param params
     * {"minId":"-1","maxId“：”1000“}
     * 为-1则是从上次拉单的id位置开始拉单，第一次拉则从0开始拉
     * @return
     */
    @XxlJob("orderRefundCompare")
    public ReturnT<String> orderRefundCompare(String params) {
        Integer minId = -1;
        Integer maxId = null;
        Integer batch = null;
        if (StringUtils.isNotBlank(params)){
            JSONObject jsonObject = JSONObject.parseObject(params);
            minId = jsonObject.getInteger("minId");
            batch = jsonObject.getInteger("batch");
            maxId = jsonObject.get("maxId") != null ? jsonObject.getInteger("maxId") : null;
        }
        walletOrderRefundCompareManagerService.walletOrderRefundCompare(minId, maxId, batch);
        return ReturnT.SUCCESS;
    }
}
