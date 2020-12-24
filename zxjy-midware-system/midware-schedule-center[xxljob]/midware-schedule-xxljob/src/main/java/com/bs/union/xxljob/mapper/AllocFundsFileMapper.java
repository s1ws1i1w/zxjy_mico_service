package com.hdyl.schedule.xxljob.mapper;

import com.hdyl.schedule.xxljob.entity.pojo.AllocFundsFileEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * alloc_funds_file 持久化层
 * 
 * @author : zhouyibin  gralves@163.com 
 * @date : 2020/09/21 20:21
 */
@Mapper
public interface AllocFundsFileMapper extends BaseMapper<AllocFundsFileEntity, Long> {

}