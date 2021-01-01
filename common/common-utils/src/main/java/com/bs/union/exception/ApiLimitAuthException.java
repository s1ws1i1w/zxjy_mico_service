package com.bs.union.exception;

/**
 * auth校验异常
 * @author kiring
 * @data 2019/12/18 14:25
 **/
public class ApiLimitAuthException extends RuntimeException{
    public ApiLimitAuthException() {
        super();
    }

    public ApiLimitAuthException(String message) {
        super(message);
    }
}
