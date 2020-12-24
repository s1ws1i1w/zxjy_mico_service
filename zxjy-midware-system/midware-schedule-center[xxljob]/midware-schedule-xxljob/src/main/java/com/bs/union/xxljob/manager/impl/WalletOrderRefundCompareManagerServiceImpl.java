package com.hdyl.schedule.xxljob.manager.impl;

import com.alibaba.fastjson.JSONObject;
import com.hdyl.merchant.union.common.utils.DingTalkUtil;
import com.hdyl.schedule.xxljob.common.constants.RedisConstant;
import com.hdyl.schedule.xxljob.entity.pojo.WalletOrderInfoEntity;
import com.hdyl.schedule.xxljob.entity.pojo.WalletOrderRefundEntity;
import com.hdyl.schedule.xxljob.manager.WalletOrderRefundCompareManagerService;
import com.hdyl.schedule.xxljob.service.WalletOrderInfoService;
import com.hdyl.schedule.xxljob.service.WalletOrderRefundService;
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
public class WalletOrderRefundCompareManagerServiceImpl implements WalletOrderRefundCompareManagerService {

    private final WalletOrderInfoService walletOrderInfoService;
    private final WalletOrderRefundService walletOrderRefundService;
    private final StringRedisTemplate redisTemplate;

    public WalletOrderRefundCompareManagerServiceImpl(WalletOrderInfoService walletOrderInfoService, WalletOrderRefundService walletOrderRefundService, StringRedisTemplate redisTemplate) {
        this.walletOrderInfoService = walletOrderInfoService;
        this.walletOrderRefundService = walletOrderRefundService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void walletOrderRefundCompare(Integer minId, Integer maxId, Integer batch) {
        if (minId == -1) {
            // 不填minId的话用上次的截止id，截止id的记录已经进行过对比
            String minIdStr = redisTemplate.opsForValue().get(RedisConstant.WALLET_ORDER_REFUND_COMPARE_ID);
            if (StringUtils.isNotBlank(minIdStr)) {
                minId = Integer.parseInt(minIdStr) + 1;
            } else {
                minId = 0;
            }
        }
        if (maxId == null) {
            // 不填id的话用迁移的最大记录订单号为maxId
            String maxIdStr = redisTemplate.opsForValue().get(RedisConstant.WALLET_ORDER_REFUND_MIN_ID);
            if (StringUtils.isNotBlank(maxIdStr)) {
                maxId = Integer.parseInt(maxIdStr);
            } else {
                maxId = 0;
            }
        }
        // 把所有的数据分成10个线程去处理
        int batchSize = (maxId - minId) / 10;
        List<FutureTask<JSONObject>> futureTaskList = new ArrayList<>();
        for (int i = 0; i < batch; i++) {
            CompareOrderRefundThread task = new CompareOrderRefundThread(walletOrderInfoService, walletOrderRefundService,i * batchSize + 1 , (i+1) * batchSize);
            FutureTask<JSONObject> futureTask = new FutureTask<>(task);
            futureTaskList.add(futureTask);
            ScheduleTransferOrderThreadPoolManager.getExecutorManager().submit(futureTask);
        }
        if ((maxId - minId) % batch > 0) {
            CompareOrderRefundThread task = new CompareOrderRefundThread(walletOrderInfoService, walletOrderRefundService,batch * batchSize + 1 , maxId);
            FutureTask<JSONObject> futureTask = new FutureTask<>(task);
            futureTaskList.add(futureTask);
            ScheduleTransferOrderThreadPoolManager.getExecutorManager().submit(futureTask);
        }
        String errorCodeStr = "";
        int count = 0;
        for (FutureTask<com.alibaba.fastjson.JSONObject> futureTask : futureTaskList) {
            try {
                com.alibaba.fastjson.JSONObject jsonObject = futureTask.get();
                count += jsonObject.getInteger("success");
                errorCodeStr += jsonObject.getString("errorCodeStr");
                log.info("当前对比正确数量：{}, 对比错误OrderCode:{}", count, errorCodeStr);
            } catch (InterruptedException | ExecutionException e) {
                log.error("多线程结果获取异常", e);
            }
        }
        XxlJobLogger.log("最大id:{},对比正确数量:{},错误订单:{}", maxId, count, errorCodeStr);
        // 保存此次开始对比的截止id
        redisTemplate.opsForValue().set(RedisConstant.WALLET_ORDER_REFUND_COMPARE_ID, String.valueOf(maxId));
    }

    static class CompareOrderRefundThread implements Callable<JSONObject> {
        private WalletOrderInfoService walletOrderInfoService;
        private WalletOrderRefundService walletOrderRefundService;
        private Integer minId;
        private Integer maxId;

        public CompareOrderRefundThread(WalletOrderInfoService walletOrderInfoService, WalletOrderRefundService walletOrderRefundService, Integer minId, Integer maxId) {
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
            List<String> errorCodeList = new ArrayList<>();
            int pages = (maxId - minId) % pageSize > 0 ? (maxId - minId) / pageSize + 1 : (maxId - minId) / pageSize;
            for (int i = 0; i < pages; i++) {
                List<WalletOrderRefundEntity> orderRefundList = walletOrderRefundService.selectBetweenId(minId, maxId);
                if (orderRefundList.size() > 0) {
                    List<WalletOrderInfoEntity> walletOrderDetailList = walletOrderInfoService.selectByOrderCodeList(orderRefundList.stream().map(WalletOrderRefundEntity::getMallOrderCode).collect(Collectors.toList()));
                    Map<String, List<WalletOrderInfoEntity>> walletOrderDetailMap = walletOrderDetailList.stream().collect(Collectors.groupingBy(WalletOrderInfoEntity::getWalletOrderCode));
                    if (walletOrderDetailMap.size() > orderRefundList.size()) {
                        DingTalkUtil.sendTextMessage("迁移后的记录:" + walletOrderDetailMap.size() + " 比迁移前的记录:" + orderRefundList.size() + " 还要多", DingTalkUtil.SHENGBAO_ERROR_YABA);
                        log.error("退款订单迁移后的记录:" + walletOrderDetailMap.size() + " 比迁移前的记录:" + orderRefundList.size() + " 还要多");
                    }
                    for (WalletOrderRefundEntity orderRefund : orderRefundList) {
                        log.info("orderCode:{}", orderRefund.getMallOrderCode());
                        List<WalletOrderInfoEntity> orderDetailList = walletOrderDetailMap.get(orderRefund.getMallOrderCode());
                        if (orderDetailList == null || orderDetailList.stream().noneMatch(orderDetail -> orderDetail.getStatus() > 4)) {
                            DingTalkUtil.sendTextMessage("退款订单数据对比失败,内部订单号：" + orderRefund.getMallOrderCode(), DingTalkUtil.SHENGBAO_ERROR_YABA);
                            log.error("退款订单数据对比失败,内部订单号：" + orderRefund.getMallOrderCode());
                            errorCodeList.add(orderRefund.getMallOrderCode());
                        } else {
                            count++;
                        }
                    }
                }
            }
            jsonObject.put("success", count);
            jsonObject.put("errorCodeStr", StringUtils.join(errorCodeList, ","));
            return jsonObject;
        }
    }
}
