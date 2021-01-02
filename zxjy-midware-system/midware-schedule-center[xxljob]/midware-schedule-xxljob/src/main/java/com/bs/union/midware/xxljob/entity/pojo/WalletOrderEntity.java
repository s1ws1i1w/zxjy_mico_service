package com.bs.union.midware.xxljob.entity.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class WalletOrderEntity {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 订单金额
     */
    private BigDecimal totalAmount;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 优惠券金额
     */
    private BigDecimal couponAmount;

    /**
     * 实际支付金额
     */
    private BigDecimal actualAmount;

    /**
     * 红包金额
     */
    private BigDecimal redPacketAmount;

    /**
     * 支付渠道费用
     */
    private BigDecimal payChannelFee;

    /**
     * 分佣金额
     */
    private BigDecimal splitFlowAmount;

    /**
     * 平台服务分佣
     */
    private BigDecimal serviceSplitFlow;

    /**
     * 招商人分佣
     */
    private BigDecimal agentSplitFlow;

    /**
     * 媒体分佣
     */
    private BigDecimal mediaSplitFlow;

    /**
     * 付款人标识
     */
    private Long payerId;

    /**
     * 收款人标识
     */
    private Long payeeId;

    /**
     * 状态
     * 0:支付中
     * 1:已支付
     * 2:支付失败
     * 5:退款中
     * 6:退款成功
     * 7:冻结
     * 8:退款失败
     */
    private Integer status;

    /**
     * 创建时间
     */
    protected Long createTime;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 修改时间
     */
    private Long updateTime;

    /**
     * 支付方式
     * 0:微信小程序
     * 1:支付宝
     */
    private Integer payWay;

    /**
     * 支付渠道
     * 0:易宝
     * 1:银联
     */
    private Integer payChannel;

    /**
     * 交易渠道流水号
     */
    private String dealId;

    /**
     * 支付时间
     */
    private Long payTime;

    /**
     * 支付人openId
     */
    private String payerOpenId;

    /**
     * 支付信息json
     */
    private String payInfo;

    /**
     * 支付内容
     */
    private String content;

    /**
     * 外部交易号
     */
    private String outTradeCode;

    /**
     * 内部交易号
     */
    private String walletOrderCode;

    /**
     * 同步回调url
     */
    private String synReturnUrl;

    /**
     * 异步通知url
     */
    private String asynReturnUrl;

    /**
     * 订单来源id（clientId）
     */
    private String orderSourceCode;

    /**
     * 招商人id
     */
    private Long aid;

    /**
     * 推广id
     */
    private Long mid;

    /**
     * 订单类型，0当面付
     */
    private Integer orderType;

    /**
     * 订单媒体自定义标签
     */
    private String mtags;

    /**
     * 下单时的分佣比例
     */
    private String commisionRatio;

    /**
     * 备注
     */
    private String remark;

    /**
     * 微信订单号
     */
    private String transactionId;

    /**
     * 支付场景 0:微信扫码,1:微信小程序,2:支付宝扫码,3:商家主动扫码 4:红人装扫码
     */
    private Integer scene;

    /**
     * 店铺id
     */
    private Long merchantId;

    /**
     * 是否扫码支付
     * 0:否
     * 1:是
     */
    private Integer scanPay;

    /**
     * 佣金
     */
    private BigDecimal commissionFee;

    /**
     * 总手续费
     */
    private BigDecimal totalServiceCharge;

    /**
     * 商家所得
     */
    private BigDecimal merchantReceivable;

    /**
     * 买家id
     */
    private Long payerUserId;

    /**
     * 店主id
     */
    private Long merchantUserId;
}
