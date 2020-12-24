package com.hdyl.schedule.xxljob.mapper;

import com.hdyl.schedule.xxljob.entity.pojo.AllocFundsLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * alloc_funds_log 持久化层
 * 
 * @author : zhouyibin  gralves@163.com 
 * @date : 2020/09/20 22:50
 */
@Mapper
public interface AllocFundsLogMapper extends BaseMapper<AllocFundsLogEntity, Long> {

    /**
     * 根据日期统计日期订单总金额
     *
     * @param date 如 20200920
     * @return 总金额
     * @author 齐高新
     * @date 2020年9月21日
     */
    BigDecimal sumBillAmountByDate(@Param("date") String date);

    /**
     * 根据日期统计日期内订单总数量
     *
     * @param date 如 20200920
     * @return 总数量
     * @author qigaoxin
     * @date 2020年9月21日
     */
    BigDecimal sumBillNumberByDate(@Param("date") String date);

    /**
     * 根据日期查询该天是否存在差异/异常
     *
     * @param date 如 20200920
     * @return 是否有异常？
     * @author qigaoxin
     * @date 2020年9月21日
     */
    int isDiffByDate(@Param("date") String date);


    /**
     * 统计金额异常总订单数
     * @param date 如 20200920
     * @return 数量
     * @author qigaoxin
     * @date 2020年9月22日
     */
    Integer sumDiffAmountNumberByDate(@Param("date") String date);

    /**
     * 统计手续费异常订单数
     *
     * @param date 如 20200920
     * @return 订单数
     * @author qigaoxin
     * @date 2020年9月22日
     */
    Integer sumDiffFeeNumberByDate(@Param("date") String date);

    /**
     * 统计除手续费和订单金额异常外, 其他异常
     *
     * @param date 如 20200920
     * @return 订单数
     * @author qigaoxin
     * @date 2020年9月22日
     */
    Integer sumDiffOtherNumberByDate(@Param("date") String date);

}