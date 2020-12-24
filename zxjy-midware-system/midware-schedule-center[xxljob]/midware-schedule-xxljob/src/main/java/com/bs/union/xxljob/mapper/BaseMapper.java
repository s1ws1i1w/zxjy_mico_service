package com.hdyl.schedule.xxljob.mapper;


import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author : zhouyibin
 * @date : 2018/10/19 15:20
 */
public interface BaseMapper<T, ID extends Serializable> {

    /**
     * 保存实体
     *
     * @param entity entity
     * @return 保存结果
     */
    int save(T entity);

    /**
     * 保存实体
     *
     * @param map map
     * @return 保存结果
     */
    int save(Map<String, Object> map);

    /**
     * 批量插入
     *
     * @param list list
     * @return 保存结果
     */
    int saveBatch(List<T> list);

    /**
     * 更新实体
     *
     * @param entity entity
     * @return 更新结果
     */
    int update(T entity);

    /**
     * 更新实体
     *
     * @param map map
     * @return 更新结果
     */
    int update(Map<String, Object> map);

    /**
     * 根据id删除实体
     *
     * @param id id
     * @return 删除结果
     */
    int delete(ID id);

    /**
     * 条件删除实体
     *
     * @param map id
     * @return 删除结果
     */
    int delete(Map<String, Object> map);

    /**
     * id批量删除
     *
     * @param id id数据
     * @return 删除结果
     */
    int deleteBatch(ID[] id);

    /**
     * 批量删除
     *
     * @param id id
     * @return 删除结果
     */
    int deleteBatch(Object id);

    /**
     * 批量删除
     *
     * @param entity list
     * @return 删除结果
     */
    int deleteList(List<T> entity);

    /**
     * id查询
     *
     * @param id id
     * @return T
     */
    T findOne(ID id);

    /**
     * 条件查询
     *
     * @param entity entity
     * @return list
     */
    List<T> findList(T entity);

    /**
     * 条件查询
     *
     * @param map 参数列表
     * @return list
     */
    List<T> findList(Map<String, Object> map);

    /**
     * 分页查询
     *
     * @param pageQuery 分页参数
     * @return 分页结果
     */
    List<T> findPage(PageInfo pageQuery);

    /**
     * 查询全部
     *
     * @return list
     */
    List<T> findAllList();

    /**
     * 查询数量
     *
     * @param entity entity
     * @return 数量
     */
    int count(T entity);

    /**
     * 判断实体是否存在
     *
     * @param entity entity
     * @return bool
     */
    boolean exist(T entity);

}
