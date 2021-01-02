package com.bs.union.midware.responsecode;

/**
 * 响应枚举的接口定义
 *
 * @author Godzilla
 * @create 2018-11-03 11:12
 */
public interface ResponseCode {
    /**
     * 获取响应码
     *
     * @return
     */
    String getCode();

    /**
     * 获取响应信息
     *
     * @return
     */
    String getDescribe();
}
