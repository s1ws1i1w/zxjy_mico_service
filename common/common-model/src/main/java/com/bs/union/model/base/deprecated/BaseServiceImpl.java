package com.bs.union.model.base.deprecated;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hdyl.merchant.union.common.models.enums.responsecode.EResponseCode;
import com.hdyl.merchant.union.common.models.exception.BusinessException;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.List;

/**
 * Service抽象类
 *
 * @author <a href="mailto:ludezh@dingtalk.com"> ludezh </a>
 * @version 1.0.0   2018/11/15,10:33
 * @since 1.0.0     2018/11/15,10:33
 */
@Deprecated
@Transactional(readOnly = true, rollbackFor = Exception.class)
public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {
    /**
     * 注入通用mapper
     */
    @Autowired
    protected Mapper<T> mapper;

    /**
     * 抽象方法
     *
     * @return
     */
    public Mapper<T> getMapper() {
        return mapper;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int delete(ID id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int delete(T record) {
        return mapper.delete(record);
    }

    @Override
    public T getById(ID id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int update(T record) {
        /**
         * 只是更新新的model中不为空的字段。
         */
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int add(T record) {
        /**
         * 会使用数据库默认值
         */
        return mapper.insertSelective(record);
    }

    @Override
    public int count(T e) {
        if (null == e) {
            throw new BusinessException(EResponseCode.SYSTEM_PARAM_MISSING);
        }
        return mapper.selectCount(e);
    }

    @Override
    public int count(Example example) {
        if (null == example) {
            throw new BusinessException(EResponseCode.SYSTEM_PARAM_MISSING);
        }
        return mapper.selectCountByExample(example);
    }

    @Override
    public T get(T e) {
        if (null == e) {
            throw new BusinessException(EResponseCode.SYSTEM_PARAM_MISSING);
        }
        return mapper.selectOne(e);
    }

    @Override
    public T get(Example example) {
        if (null == example) {
            throw new BusinessException(EResponseCode.SYSTEM_PARAM_MISSING);
        }
        return mapper.selectOneByExample(example);
    }

    @Override
    public List<T> getList(T e) {
        if (null == e) {
            throw new BusinessException(EResponseCode.SYSTEM_PARAM_MISSING);
        }
        return mapper.select(e);
    }

    @Override
    public List<T> getList(Example example) {
        if (null == example) {
            throw new BusinessException(EResponseCode.SYSTEM_PARAM_MISSING);
        }
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> getFindAll() {
        return mapper.selectAll();
    }

    @Override
    public PageInfo<T> getPage(int pageNum, int pageSize, String orders) {
        PageInfo<T> pageInfo = PageHelper.startPage(pageNum, pageSize).setOrderBy(orders).doSelectPageInfo(() -> this.getMapper().selectAll());
        return pageInfo;
    }

    @Override
    public List<T> getPage(T e, int page, int pageSize) {
        if (null == e) {
            throw new BusinessException(EResponseCode.SYSTEM_PARAM_MISSING);
        }
        PageHelper.startPage(page, pageSize);
        return mapper.select(e);
    }

    @Override
    public List<T> getPage(T e, int page, int pageSize, String orderBy) {
        if (null == e) {
            throw new BusinessException(EResponseCode.SYSTEM_PARAM_MISSING);
        }
        PageHelper.startPage(page, pageSize, orderBy);
        return mapper.select(e);
    }


    @Override
    public List<T> getPage(Example example, int page, int pageSize) {
        if (null == example) {
            throw new BusinessException(EResponseCode.SYSTEM_PARAM_MISSING);
        }
        RowBounds rowBounds = new RowBounds((page - 1) * pageSize, pageSize);
        return mapper.selectByExampleAndRowBounds(example, rowBounds);
    }
}
