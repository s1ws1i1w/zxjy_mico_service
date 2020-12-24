package com.hdyl.schedule.xxljob.entity.pojo;

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
 * mall_order_refund_record 实体类
 *
 * @author : zhouyibin   gralves@163.com
 * @date : 2020/09/08 16:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel("点单退款订单实体")
public class WalletOrderRefundEntity implements Serializable {
    /**
     * id id
     **/
    @ApiModelProperty("id")
    private Long id;

    /**
     * 店铺id merchant_id
     **/
    @ApiModelProperty("店铺id")
    private Long merchantId;

    /**
     * 订单编号 mall_order_code
     **/
    @ApiModelProperty("订单编号")
    private String mallOrderCode;

    /**
     * 退款原因 refund_reason
     **/
    @ApiModelProperty("退款原因")
    private String refundReason;

    /**
     * 退款金额 refund_amount
     **/
    @ApiModelProperty("退款金额")
    private BigDecimal refundAmount;

    /**
     * 应承担手续费 service_charge
     **/
    @ApiModelProperty("应承担手续费")
    private BigDecimal serviceCharge;

    /**
     * 额外承担的手续费 service_charge_extra
     **/
    @ApiModelProperty("额外承担的手续费")
    private BigDecimal serviceChargeExtra;

    /**
     * 总手续费 service_charge_total
     **/
    @ApiModelProperty("总手续费")
    private BigDecimal serviceChargeTotal;

    /**
     * 到期时间 expiration_time
     **/
    @ApiModelProperty("到期时间")
    private Date expirationTime;

    /**
     * 取单号 order_num
     **/
    @ApiModelProperty("取单号")
    private Integer orderNum;

    /**
     * 退款状态 5: 退款中, 6:退款成功, 7: 冻结, 8: 退款失败 order_status
     **/
    @ApiModelProperty("退款状态 5: 退款中, 6:退款成功, 7: 冻结, 8: 退款失败")
    private String orderStatus;

    /**
     * 运营后台评论 comment
     **/
    @ApiModelProperty("运营后台评论")
    private String comment;

    /**
     * 处理人 handle_person
     **/
    @ApiModelProperty("处理人")
    private String handlePerson;

    /**
     * 处理时间 handle_time
     **/
    @ApiModelProperty("处理时间")
    private Date handleTime;

    /**
     * 创建时间 create_time
     **/
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新时间 update_time
     **/
    @ApiModelProperty("更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}