package com.bs.union.exception.handler;

import com.alibaba.fastjson.JSON;

import com.bs.union.base.ResultVO;
import com.bs.union.exception.BusinessException;
import com.bs.union.exception.LogicException;
import com.bs.union.responsecode.RsCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 业务异常处理器
 *
 * @author zhouyibin
 * @date 2019/5/24
 */
@Slf4j
@Component("CommonExceptionHandler")
@RestControllerAdvice
public class BusinessExceptionHandler {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(DuplicateKeyException.class)
    public ResultVO<Object> handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("主键重复: {}", JSON.toJSONString(e));
        return ResultVO.general(RsCode.CommonRsCode.DATABASE_DUPLICATE_KEY);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultVO<Object> handleDuplicateKeyException(HttpRequestMethodNotSupportedException e) {
        return ResultVO.general(RsCode.CommonRsCode.SYSTEM_METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO<Object> handleRRException(BusinessException e) {
        log.error("业务异常,错误描述：\n", e);
        return ResultVO.errorMsg(e.getDescription());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO<Object> handleException(Exception e) {
        log.error("系统异常，错误描述：{},\n", e.getMessage(), e);
        return ResultVO.general(RsCode.CommonRsCode.SYSTEM_ERROR);
    }

    /**
     * 数据类型解析异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler({BindException.class})
    public ResultVO<Object> handleBindException(BindException e) {
        ResultVO<Object> resultVO = ResultVO.general(RsCode.CommonRsCode.SYSTEM_PARAM_ERROR);
        String msg = null;
        if (null != e.getBindingResult().getFieldError()) {
            msg = e.getBindingResult().getFieldError().getDefaultMessage();
        }
        if (StringUtils.isBlank(msg)) {
            if (null != e.getBindingResult().getGlobalError()) {
                msg = e.getBindingResult().getGlobalError().getDefaultMessage();
            }
        }
        resultVO.setMsg(msg);
        return resultVO;
    }

    /**
     * 数据合法性校验异常处理,检验对象加上@Validated
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<RsCode> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ResultVO<RsCode> resultVO = ResultVO.general(RsCode.CommonRsCode.SYSTEM_PARAM_ERROR);
        String msg = null;
        if (null != e.getBindingResult().getFieldError()) {
            msg = e.getBindingResult().getFieldError().getDefaultMessage();
        }
        if (StringUtils.isBlank(msg)) {
            if (null != e.getBindingResult().getGlobalError()) {
                msg = e.getBindingResult().getGlobalError().getDefaultMessage();
            }
        }
        resultVO.setMsg(msg);
        return resultVO;
    }

    public ResultVO<RsCode> handle(Throwable throwable) {
        return ResultVO.general(RsCode.CommonRsCode.SYSTEM_ERROR);
    }

//    /**
//     * 处理权限认证异常
//     *
//     * @return 自定义返回
//     * @author gralves
//     * @date 2020/6/23
//     */
//    @ResponseStatus(HttpStatus.OK)
//    @ExceptionHandler(UnauthorizedException.class)
//    public ResultVO<RsCode> handleUnauthorizedException() {
//        return ResultVO.general(CommonRsCode.PERMISSION_INVALID);
//    }


    /**
     * 处理 Assert 校验异常
     * @param e 错误
     * @return 自定义返回
     * @author qigaoxin
     * @date 2020年8月5日 11点43分
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LogicException.class)
    public ResultVO<RsCode> handleLogicException(LogicException e) {
        return ResultVO.general(e.getBaseResponse());
    }

}
