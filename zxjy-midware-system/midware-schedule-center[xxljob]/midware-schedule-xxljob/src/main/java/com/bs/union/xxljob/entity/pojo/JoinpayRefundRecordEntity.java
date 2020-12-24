package com.hdyl.schedule.xxljob.entity.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * joinpay_refund_record 实体类
 *
 * @author : zhouyibin   gralves@163.com
 * @date : 2020/04/07 15:59
 */
@Data
public class JoinpayRefundRecordEntity implements Serializable {
    private Long id;

    /**
     * 商户id merchant_id
     **/
    private Long merchantId;

    /**
     * 商户编号 merchant_no
     **/
    private String merchantNo;

    /**
     * 点餐/订单编号 merchant_order_no
     **/
    private String merchantOrderNo;

    /**
     * 退款订单编号 refund_order_no
     **/
    private String refundOrderNo;

    /**
     * 退款金额 refund_amount
     **/
    private BigDecimal refundAmount;

    /**
     * 退款流水号 refund_trx_no
     **/
    private String refundTrxNo;

    /**
     * 退款信息 alt_ref_info
     **/
    private String altRefInfo;

    /**
     * 分账方订单号 alt_order_no
     **/
    private String altOrderNo;

    /**
     * 退款状态 0: 退款中, 1: 退款成功, 2:退款失败 refund_status
     **/
    private String refundStatus;

    /**
     * 退款编码 code
     **/
    private String code;

    /**
     * 退款编码说明 code_msg
     **/
    private String codeMsg;

    /**
     * 创建时间 create_time
     **/
    private Date createTime;

    /**
     * 更新时间 update_time
     **/
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}