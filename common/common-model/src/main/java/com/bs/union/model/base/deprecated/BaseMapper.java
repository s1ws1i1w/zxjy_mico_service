package com.bs.union.model.base.deprecated;

import com.hdyl.merchant.union.common.models.entity.base.BaseEntity;

/**
 * Mapper 基础接口
 *
 * @author <a href="mailto:ludezheng@hongrz.com"> ludezh </a>
 * @version 1.0.0   2018/10/16,11:19
 * @since 1.0.0     2018/10/16,11:19
 */
@Deprecated
public interface BaseMapper<E extends BaseEntity> {

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 全字段添加
     *
     * @param entity
     * @return
     */
    long insert(E entity);

    /**
     * 选择性字段添加
     *
     * @param entity
     * @return
     */
    long insertSelective(E entity);

    /**
     * 根据id获取记录
     *
     * @param id
     * @return
     */
    E selectByPrimaryKey(Long id);

    /**
     * 根据id 选择性字段更新
     *
     * @param entity
     * @return
     */
    int updateByPrimaryKeySelective(E entity);

    /**
     * 根据id 全字段更新
     *
     * @param entity
     * @return
     */
    int updateByPrimaryKey(E entity);

}
