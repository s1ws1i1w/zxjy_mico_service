package com.bs.union.model.base.deprecated;

import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.List;


/**
 * Service基类
 *
 * @author <a href="mailto:ludezh@dingtalk.com"> ludezh </a>
 * @version 1.0.0   2018/11/15,10:33
 * @since 1.0.0     2018/11/15,10:33
 */
@Deprecated
public interface BaseService<T, ID extends Serializable> {
    /**
     * 按主键删除
     *
     * @param id
     * @return
     */
    int delete(ID id);

    /**
     * 按条件删除
     *
     * @param record
     * @return
     */
    int delete(T record);

    /**
     * 按主键查询
     *
     * @param id
     * @return
     */
    T getById(ID id);

    /**
     * 按条件更新数据
     *
     * @param record
     * @return
     */
    int update(T record);

    /**
     * 添加记录
     *
     * @param record
     * @return
     */
    int add(T record);

    /**
     * 根据数据表实体类 获取记录数
     *
     * @param e
     * @return
     */
    int count(T e);

    /**
     * 根据条件实例对象 获取记录数
     *
     * @param example
     * @return
     */
    int count(Example example);

    /**
     * 根据数据表实体类 获取单条数据
     *
     * @param e
     * @return
     */
    T get(T e);

    /**
     * 根据条件实例对象 获取单条记录
     *
     * @param example
     * @return
     */
    T get(Example example);

    /**
     * 根据数据表实体类 获取列表
     *
     * @param e
     * @return
     */
    List<T> getList(T e);

    /**
     * 根据条件实例对象 获取列表
     *
     * @param example
     * @return
     */
    List<T> getList(Example example);

    /**
     * 查询所有
     *
     * @return
     */
    List<T> getFindAll();

    /**
     * 查询所有数据进行分页
     *
     * @param pageNum  第几页
     * @param pageSize 页大小
     * @param orders   排序字段 格式：id desc
     * @return
     */
    PageInfo<T> getPage(int pageNum, int pageSize, String orders);

    /**
     * PageHelper 获取分页
     *
     * @param e
     * @param page
     * @param pageSize
     * @return
     */
    List<T> getPage(T e, int page, int pageSize);

    /**
     * PageHelper 获取分页
     *
     * @param e
     * @param page
     * @param pageSize
     * @param orderBy  如 create_time desc
     * @return
     */
    List<T> getPage(T e, int page, int pageSize, String orderBy);

    /**
     * 根据条件实例对象 获取分页
     *
     * @param example
     * @param page
     * @param pageSize
     * @return
     */
    List<T> getPage(Example example, int page, int pageSize);
}
