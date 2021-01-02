package com.bs.union.midware.xxljob.entity.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * alloc_funds_log 实体类
 *
 * @author : zhouyibin   gralves@163.com
 * @date : 2020/09/20 22:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel("")
public class AllocFundsLogEntity implements Serializable {
    private Long id;

    /**
	 * 交易统一跟踪号, 目前没有, 使用wallet_order_no trade_no
	 **/
    @ApiModelProperty("交易统一跟踪号, 目前没有, 使用wallet_order_no")
    private String tradeNo;

    /**
	 * e省宝订单号 wallet_order_code
	 **/
    @ApiModelProperty("e省宝订单号")
    private String walletOrderCode;

    /**
	 * 支付平台交易支付号 platfrom_payment_no
	 **/
    @ApiModelProperty("支付平台交易支付号")
    private Long platfromPaymentNo;

    /**
	 * 支付平台分账流水号 platform_ledger_no
	 **/
    @ApiModelProperty("支付平台分账流水号")
    private String platformLedgerNo;

    /**
	 * 支付平台记录的订单总额 platform_order_amount
	 **/
    @ApiModelProperty("支付平台记录的订单总额")
    private BigDecimal platformOrderAmount;

    /**
	 * 支付平台记录的手续费 platform_fee
	 **/
    @ApiModelProperty("支付平台记录的手续费")
    private BigDecimal platformFee;

    /**
	 * 支付平台记录的商户分账金额 platform_ledger_amount
	 **/
    @ApiModelProperty("支付平台记录的商户分账金额")
    private BigDecimal platformLedgerAmount;

    /**
	 * 系统记录订单总额 order_amount
	 **/
    @ApiModelProperty("系统记录订单总额")
    private BigDecimal orderAmount;

    /**
	 * 系统记录的实际分账金额 ledger_amount
	 **/
    @ApiModelProperty("系统记录的实际分账金额")
    private BigDecimal ledgerAmount;

    /**
	 * 系统记录的订单手续费 fee
	 **/
    @ApiModelProperty("系统记录的订单手续费")
    private BigDecimal fee;

    /**
	 * 订单类型 - 支付, 退款 order_type
	 **/
    @ApiModelProperty("订单类型 - 1支付, 2退款")
    private Integer orderType;

    /**
	 * 订单状态 - 完成, 未完成 order_status
	 **/
    @ApiModelProperty("订单状态 - 1完成, 2未完成")
    private Integer orderStatus;

    /**
	 * 订单对账是否出现异常 is_exception
	 **/
    @ApiModelProperty("订单对账是否出现异常")
    private Integer isException;

    /**
	 * 订单异常类型 - 1交易金额异常 2手续费异常 3订单号异常 exception_type
	 **/
    @ApiModelProperty("订单异常类型 - 1交易金额异常 2手续费异常 3补单异常(汇聚有我没有) 4漏单异常(我们有汇聚没有)")
    private Integer exceptionType;

    /**
	 * 订单差异金额 exception_diff_amount
	 **/
    @ApiModelProperty("订单差异金额")
    private BigDecimal exceptionDiffAmount;

    /**
	 * 商家id merchant_id
	 **/
    @ApiModelProperty("商家id")
    private Long merchantId;

    /**
	 * 用户id user_id
	 **/
    @ApiModelProperty("用户id")
    private Long userId;

    /**
	 * 备注 memo
	 **/
    @ApiModelProperty("备注")
    private String memo;

    /**
	 * 订单时间 order_time
	 **/
    @ApiModelProperty("订单时间")
    private Date orderTime;

    /**
	 * 分组日期 如 20200829 group_date
	 **/
    @ApiModelProperty("分组日期 如 20200829")
    private Integer groupDate;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
