package com.bs.union.midware.xxljob.entity.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class WalletOrderDetailEntity {

    /**
     * 主键id
     */
    private Long id;

    /**
     * wallet_order表的id
     */
    private Long orderId;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 优惠券金额
     */
    private BigDecimal couponAmount;

    /**
     * 创建时间
     */
    protected Long createTime;

    /**
     * 修改时间
     */
    private Long updateTime;

    /**
     * 招商人id
     */
    private Long aid;

    /**
     * 下单时的分佣比例
     */
    private String commissionRatio;

    /**
     * 佣金
     */
    private BigDecimal commissionFee;

    /**
     * 行业名称
     */
    private String industryName;
    /**
     * 省 province
     **/
    private String province;

    /**
     * 市 city
     **/
    private String city;

    /**
     * 区/县 area
     **/
    private String area;

    /**
     * 详细地址 address
     **/
    private String address;
}
