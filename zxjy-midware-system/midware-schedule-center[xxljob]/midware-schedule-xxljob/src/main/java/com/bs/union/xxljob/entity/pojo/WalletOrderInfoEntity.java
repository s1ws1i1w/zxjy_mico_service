package com.hdyl.schedule.xxljob.entity.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class WalletOrderInfoEntity {

    /**
     * 主键id
     */
    private Long id;

    /**
     * wallet_order表的id
     */
    private Long orderId;

    /**
     * 订单金额
     */
    private BigDecimal totalAmount;

    /**
     * 实际支付金额
     */
    private BigDecimal actualAmount;

    /**
     * 支付渠道费用
     */
    private BigDecimal payChannelFee;

    /**
     * 状态
     * 0:支付中
     * 1:已支付
     * 2:支付失败
     */
    private Integer status;

    /**
     * 创建时间
     */
    protected Long createTime;

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
     * 外部交易号
     */
    private String outTradeCode;

    /**
     * 内部交易号
     */
    private String walletOrderCode;

    /**
     * 订单来源id（clientId）
     */
    private String orderSourceCode;

    /**
     * 订单类型，0当面付
     */
    private Integer orderType;

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
     * 买家名称
     */
    private String payerUserName;

    /**
     * unionId
     */
    private String unionId;

    /**
     * 店主id
     */
    private Long merchantUserId;

    /**
     * 退款原因
     */
    private String refundReason;

    /**
     * 处理人
     */
    private String handlePerson;

    /**
     * 处理时间
     */
    private Long handleTime;

    /**
     * 店铺简称（20个字以内） store_name_abbreviation
     **/
    private String storeNameAbbreviation;
}
