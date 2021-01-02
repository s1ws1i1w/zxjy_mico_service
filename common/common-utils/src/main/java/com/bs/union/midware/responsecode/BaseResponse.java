package com.bs.union.midware.responsecode;

/**
 * 通用返回
 * <p>
 * 成功code        200
 * 通用异常code     500
 * 商户SaaS服务 1000~1999
 * CRM系统  2000~2499
 * 中台系统  2500~2999
 * 支付系统  3000~3499
 * 推荐系统  3500~4999
 * 内容系统  5000~5499
 * 佣金系统  5500~5999
 * 数据库/缓存 100~199
 *
 * @author gralves
 * @date 2020/6/5
 */
public interface BaseResponse {

    /**
     * 获取code
     *
     * @return code
     */
    Integer getCode();

    /**
     * 获取返回信息
     *
     * @return msg
     */
    String getMsg();
}
