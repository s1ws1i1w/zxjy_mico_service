package com.hdyl.schedule.xxljob.manager.impl;

import com.alibaba.fastjson.JSONObject;
import com.hdyl.merchant.union.common.utils.DingTalkUtil;
import com.hdyl.schedule.xxljob.common.constants.RedisConstant;
import com.hdyl.schedule.xxljob.entity.pojo.WalletOrderEntity;
import com.hdyl.schedule.xxljob.entity.pojo.WalletOrderInfoEntity;
import com.hdyl.schedule.xxljob.manager.WalletOrderCompareManagerService;
import com.hdyl.schedule.xxljob.service.WalletOrderInfoService;
import com.hdyl.schedule.xxljob.service.WalletOrderRefundService;
import com.hdyl.schedule.xxljob.service.WalletOrderService;
import com.hdyl.schedule.xxljob.threadpool.ScheduleTransferOrderThreadPoolManager;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

/**
 * @author Godzilla
 * @create 2018-10-23 16:53
 */
@Service
@Slf4j
public class WalletOrderCompareManagerServiceImpl implements WalletOrderCompareManagerService {

    private final WalletOrderService walletOrderService;
    private final WalletOrderInfoService walletOrderInfoService;
    private final WalletOrderRefundService walletOrderRefundService;
    private final StringRedisTemplate redisTemplate;

    public WalletOrderCompareManagerServiceImpl(WalletOrderService walletOrderService, WalletOrderInfoService walletOrderInfoService, WalletOrderRefundService walletOrderRefundService, StringRedisTemplate redisTemplate) {
        this.walletOrderService = walletOrderService;
        this.walletOrderInfoService = walletOrderInfoService;
        this.walletOrderRefundService = walletOrderRefundService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void walletOrderCompare(Integer minId, Integer maxId, Integer batch) {
        if (minId == -2) {
            // 不填minId的话用上次的截止id，截止id的记录已经进行过对比
            String minIdStr = redisTemplate.opsForValue().get(RedisConstant.WALLET_ORDER_COMPARE_ID);
            if (StringUtils.isNotBlank(minIdStr)) {
                minId = Integer.parseInt(minIdStr) + 1;
            } else {
                minId = 0;
            }
        }
        if (maxId == null) {
            // 不填id的话用迁移的最大记录订单号为maxId
            String maxIdStr = redisTemplate.opsForValue().get(RedisConstant.WALLET_ORDER_MIN_ID);
            if (StringUtils.isNotBlank(maxIdStr)) {
                maxId = Integer.parseInt(maxIdStr);
            } else {
                maxId = 0;
            }
        }
        // 多线程处理
        int batchSize = (maxId - minId) / batch;
        List<FutureTask<JSONObject>> futureTaskList = new ArrayList<>();
        for (int i = 0; i < batch; i++) {
            CompareOrderThread task = new CompareOrderThread(walletOrderService, walletOrderInfoService, walletOrderRefundService, i * batchSize + 1 , (i+1) * batchSize);
            FutureTask<JSONObject> futureTask = new FutureTask<>(task);
            futureTaskList.add(futureTask);
            ScheduleTransferOrderThreadPoolManager.getExecutorManager().submit(futureTask);
        }
        if ((maxId - minId) % batch > 0) {
            CompareOrderThread task = new CompareOrderThread(walletOrderService, walletOrderInfoService, walletOrderRefundService, batch * batchSize + 1 , maxId);
            FutureTask<JSONObject> futureTask = new FutureTask<>(task);
            futureTaskList.add(futureTask);
            ScheduleTransferOrderThreadPoolManager.getExecutorManager().submit(futureTask);
        }
        String errorIdStr = "";
        int count = 0;
        for (FutureTask<JSONObject> futureTask : futureTaskList) {
            try {
                com.alibaba.fastjson.JSONObject jsonObject = futureTask.get();
                count += jsonObject.getInteger("success");
                errorIdStr += jsonObject.getString("errorIdStr");
                log.info("当前对比正确数量：{}, 对比错误OrderId:{}", count, errorIdStr);
            } catch (InterruptedException | ExecutionException e) {
                log.error("多线程结果获取异常", e);
            }
        }
        XxlJobLogger.log("最大id:{},对比正确数量:{},错误订单:{}", maxId, count, errorIdStr);
        if (StringUtils.isNotBlank(errorIdStr)) {
//            DingTalkUtil.sendTextMessage("对比订单失败，订单id:" + errorIdStr, DingTalkUtil.SHENGBAO_ERROR_YABA);
        }
        // 保存此次开始对比的截止id
        redisTemplate.opsForValue().set(RedisConstant.WALLET_ORDER_COMPARE_ID, String.valueOf(maxId));
    }

    static class CompareOrderThread implements Callable<JSONObject> {
        private WalletOrderService walletOrderService;
        private WalletOrderInfoService walletOrderInfoService;
        private WalletOrderRefundService walletOrderRefundService;
        private Integer minId;
        private Integer maxId;

        public CompareOrderThread(WalletOrderService walletOrderService, WalletOrderInfoService walletOrderInfoService, WalletOrderRefundService walletOrderRefundService, Integer minId, Integer maxId) {
            this.walletOrderService = walletOrderService;
            this.walletOrderInfoService = walletOrderInfoService;
            this.walletOrderRefundService = walletOrderRefundService;
            this.minId = minId;
            this.maxId = maxId;
        }

        @Override
        public JSONObject call() throws Exception {
            JSONObject jsonObject = new JSONObject();
            int count = 0;
            int pageSize = 1000;
            List<Long> errorCodeList = new ArrayList<>();
            int pages = (maxId - minId) % pageSize > 0 ? (maxId - minId) / pageSize + 1 : (maxId - minId) / pageSize;
            for (int i = 0; i < pages; i++) {
                List<WalletOrderEntity> walletOrderList = walletOrderService.selectBetweenId(minId, maxId);
                if (walletOrderList.size() > 0) {
                    List<WalletOrderInfoEntity> walletOrderDetailList = walletOrderInfoService.selectBetweenOrderId(minId, maxId);
                    Map<Long, List<WalletOrderInfoEntity>> walletOrderDetailMap = walletOrderDetailList.stream().collect(Collectors.groupingBy(WalletOrderInfoEntity::getOrderId));
                    if (walletOrderDetailMap.size() > walletOrderList.size()) {
                        DingTalkUtil.sendTextMessage("迁移后的记录:" + walletOrderDetailMap.size() + " 比迁移前的记录:" + walletOrderList.size() + " 还要多", DingTalkUtil.SHENGBAO_ERROR_YABA);
                        log.error("迁移后的记录:" + walletOrderDetailMap.size() + " 比迁移前的记录:" + walletOrderList.size() + " 还要多");
                    }
                    for (WalletOrderEntity order : walletOrderList) {
                        log.info("orderid：{}", order.getId());
                        if (order.getStatus() == null) {
                            errorCodeList.add(order.getId());
                            log.error("{}status为空", order.getId());
                        } else {
                            List<WalletOrderInfoEntity> orderDetailList = walletOrderDetailMap.get(order.getId());
                            if (orderDetailList == null) {
                                errorCodeList.add(order.getId());
                                DingTalkUtil.sendTextMessage("支付订单数据对比没有发现迁移后订单,订单号：" + order.getId(), DingTalkUtil.SHENGBAO_ERROR_YABA);
                                log.error("支付订单数据对比没有发现迁移后订单,订单号：" + order.getId());
                            } else {
                                if (order.getStatus() > 4 && orderDetailList.stream().noneMatch(orderDetail -> orderDetail.getStatus() < 5)) {
                                    errorCodeList.add(order.getId());
                                    log.error("退款订单{}没有找到退款订单记录", order.getId());
                                    DingTalkUtil.sendTextMessage("退款订单" + order.getId() + "没有找到退款订单记录", DingTalkUtil.SHENGBAO_ERROR_YABA);
                                } else {
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
            jsonObject.put("success", count);
            jsonObject.put("errorIdStr", StringUtils.join(errorCodeList, ","));
            return jsonObject;
        }
    }
}
