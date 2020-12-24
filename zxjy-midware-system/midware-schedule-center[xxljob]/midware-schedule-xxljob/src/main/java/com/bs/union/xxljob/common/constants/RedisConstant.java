package com.hdyl.schedule.xxljob.common.constants;

import java.util.Random;

/**
 * description
 *
 * @author <a href="mailto:ludezh@dingtalk.com"> ludezh </a>
 * @version 1.0.0   2019/10/16,13:45
 * @since 1.0.0     2019/10/16,13:45
 */
public class RedisConstant {

    /**
     * 退款订单上次拉取的最小订单id
     */
    public static final String WALLET_ORDER_REFUND_MIN_ID = "WALLET_ORDER_REFUND_MIN_ID";

    /**
     * 退款订单上次对比的截止订单id
     */
    public static final String WALLET_ORDER_REFUND_COMPARE_ID = "WALLET_ORDER_REFUND_COMPARE_ID";

    /**
     * 支付订单上次拉取的最小订单id
     */
    public static final String WALLET_ORDER_MIN_ID = "WALLET_ORDER_MIN_ID";

    /**
     * 支付订单上次对比的截止订单id
     */
    public static final String WALLET_ORDER_COMPARE_ID = "WALLET_ORDER_COMPARE_ID";
}
