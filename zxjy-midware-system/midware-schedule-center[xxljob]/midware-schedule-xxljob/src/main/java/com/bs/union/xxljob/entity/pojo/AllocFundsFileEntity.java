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
 * alloc_funds_file 实体类
 * 
 * @author : zhouyibin   gralves@163.com
 * @date : 2020/09/21 20:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel("")
public class AllocFundsFileEntity implements Serializable {
    /**
	 * 主键 id
	 **/
    @ApiModelProperty("主键")
    private Long id;

    /**
	 * 账单名称 bill_name
	 **/
    @ApiModelProperty("账单名称")
    private String billName;

    /**
	 * 对账总金额 (单位秒) bill_amount
	 **/
    @ApiModelProperty("对账总金额 (单位秒)")
    private BigDecimal billAmount;

    /**
	 * 对账总笔数  bill_number
	 **/
    @ApiModelProperty("对账总笔数 ")
    private BigDecimal billNumber;

    /**
	 * 账单时间 格式20200805 bill_date
	 **/
    @ApiModelProperty("账单时间 格式20200805")
    private Integer billDate;

    /**
	 * 是否存在差异/异常 - 0不存在, 1存在 is_diff
	 **/
    @ApiModelProperty("是否存在差异/异常 - 0不存在, 1存在")
    private Integer isDiff;

    /**
	 * 对账文件下载地址 file_path
	 **/
    @ApiModelProperty("对账文件下载地址")
    private String filePath;

    /**
	 * 交易金额异常数量 diff_amount_number
	 **/
    @ApiModelProperty("交易金额异常数量")
    private Integer diffAmountNumber;

    /**
	 * 支付手续费异常数量 diff_rate_number
	 **/
    @ApiModelProperty("支付手续费异常数量")
    private Integer diffRateNumber;

    /**
	 * 其他未知异常数量 diff_other_number
	 **/
    @ApiModelProperty("其他未知异常数量")
    private Integer diffOtherNumber;

    /**
	 * 对账完成时间 verify_time
	 **/
    @ApiModelProperty("对账完成时间")
    private Date verifyTime;

    /**
	 * 记录创建时间 create_time
	 **/
    @ApiModelProperty("记录创建时间")
    private Date createTime;

    /**
	 * 记录更新时间 update_time
	 **/
    @ApiModelProperty("记录更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}