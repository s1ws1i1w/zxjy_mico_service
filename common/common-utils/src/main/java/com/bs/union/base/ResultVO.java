package com.bs.union.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.bs.union.responsecode.BaseResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

/**
 * 统一返回包装类
 *
 * @author zyx
 * @date 2020年06月05日
 */
@Data
@ApiModel("统一返回包装类")
@Accessors(chain = true)
public class ResultVO<T> {

    /**
     * 返回信息
     */
    @ApiModelProperty("message")
    private String msg;

    /**
     * 返回码
     */
    @ApiModelProperty("code")
    private Integer code;

    /**
     * 返回数据
     */
    @ApiModelProperty("data")
    private T data;

    /**
     * 成功返回
     *
     * @return 统一返回包装类
     */
    public static <T> ResultVO<T> ok() {
        return new ResultVO<T>()
                .setCode(HttpStatus.OK.value())
                .setMsg(HttpStatus.OK.getReasonPhrase());
    }

    /**
     * 成功返回
     *
     * @param data 返回数据
     * @return 统一返回包装类
     */
    public static <T> ResultVO<T> ok(T data) {
        return new ResultVO<T>()
                .setCode(HttpStatus.OK.value())
                .setMsg(HttpStatus.OK.getReasonPhrase())
                .setData(data);
    }

    /**
     * 成功返回
     *
     * @param msg 返回信息
     * @return 统一返回包装类
     */
    public static <T> ResultVO<T> okMsg(String msg) {
        return new ResultVO<T>()
                .setCode(HttpStatus.OK.value())
                .setMsg(msg);
    }

    /**
     * 未授权返回
     *
     * @return 统一返回包装类
     */
    public static <T> ResultVO<T> unauthorized() {
        return new ResultVO<T>()
                .setCode(HttpStatus.UNAUTHORIZED.value())
                .setMsg("您的网络出错了，请重新登录");
    }

    /**
     * 未授权返回
     *
     * @param data 返回数据
     * @return 统一返回包装类
     */
    public static <T> ResultVO<T> unauthorized(T data) {
        return new ResultVO<T>()
                .setCode(HttpStatus.UNAUTHORIZED.value())
                .setMsg(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .setData(data);
    }

    /**
     * 频繁请求返回
     *
     * @return 统一返回包装类
     */
    public static <T> ResultVO<T> tooManyRequest() {
        return new ResultVO<T>()
                .setCode(HttpStatus.TOO_MANY_REQUESTS.value())
                .setMsg(HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
    }

    /**
     * 频繁请求返回
     *
     * @param data 返回数据
     * @return 统一返回包装类
     */
    public static <T> ResultVO<T> tooManyRequest(T data) {
        return new ResultVO<T>()
                .setCode(HttpStatus.TOO_MANY_REQUESTS.value())
                .setMsg(HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase())
                .setData(data);
    }

    /**
     * 系统异常返回
     *
     * @return 统一返回包装类
     */
    public static <T> ResultVO<T> error() {
        return new ResultVO<T>()
                .setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    /**
     * 系统异常返回
     *
     * @param data 返回数据
     * @return 统一返回包装类
     */
    public static <T> ResultVO<T> error(T data) {
        return new ResultVO<T>()
                .setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .setData(data);
    }

    /**
     * 系统异常返回
     *
     * @param msg 返回的错误数据
     * @return 统一返回包装类
     */
    public static <T> ResultVO<T> errorMsg(String msg) {
        return new ResultVO<T>()
                .setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setMsg(msg);
    }

    /**
     * 自定义返回
     *
     * @param baseResponse 通用返回枚举
     * @return 统一返回包装类
     */
    public static <T> ResultVO<T> general(BaseResponse baseResponse) {
        return new ResultVO<T>()
                .setCode(baseResponse.getCode())
                .setMsg(baseResponse.getMsg());
    }

    /**
     * 判断是否成功
     * @return boolean
     * @date 2020/6/15 17:18
     */
    @JsonIgnore
    @JSONField(serialize = false)
    public boolean isSuccess(){
        return HttpStatus.OK.value() == code;
    }

}
