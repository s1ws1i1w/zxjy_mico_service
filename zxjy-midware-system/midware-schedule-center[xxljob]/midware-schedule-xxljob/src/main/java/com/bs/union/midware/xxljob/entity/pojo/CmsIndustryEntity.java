package com.bs.union.midware.xxljob.entity.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 商家分类实体类
 *
 * @author tt
 */
@Data
@Accessors(chain = true)
public class CmsIndustryEntity {
    /**
     * id
     */
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 父级id
     */
    private Long pid;

    /**
     * 上下架状态
     *
     * @see com.hdyl.merchant.union.common.models.enums.merchant.IndustryStatusEnum
     */
    private Integer status;

    /**
     * 删除状态
     *
     * @see com.hdyl.merchant.union.common.models.enums.common.IsDeleteEnum
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * icon
     */
    private String icon;

    /**
     * 是否推荐到首页
     *
     * @see com.hdyl.merchant.union.common.models.enums.merchant.IndustryRecommendEnum
     */
    private Integer recommend;

    /**
     * 排序值
     */
    private Integer sort;
}
