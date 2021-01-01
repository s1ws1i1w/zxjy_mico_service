package com.bs.union.exception;

import com.bs.union.responsecode.EResponseCode;
import com.bs.union.responsecode.ResponseCode;
import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * @author ludezh
 * @company Information Technology
 * @description 业务异常类, 主要用于将错误信息返回给前端
 * @since 2017/11/24
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessException extends BaseException {
    private String cause;

    private static final long serialVersionUID = 1735388308027428062L;

    public BusinessException(String code, String description, boolean isRecord, Throwable cause) {
        super(code, description, isRecord, cause);
    }

    public BusinessException(String code, String description, boolean isRecord) {
        super(code, description, isRecord);
    }

    public BusinessException(String code, String description) {
        super(code, description);
    }

    public BusinessException(String description, boolean isRecord) {
        super(description, isRecord);
    }

    public BusinessException(String description) {
        super(description);
    }

    public BusinessException(String description, boolean isRecord, Throwable cause) {
        super(description, isRecord, cause);
    }

    public BusinessException(String description, Throwable cause) {
        super(description, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(ResponseCode resp) {
        super(resp.getCode(), resp.getDescribe());
    }

    public BusinessException(EResponseCode responseCode, String cause) {
        super(responseCode.getCode(), responseCode.getDescribe());
        this.cause = cause;
    }

    public BusinessException(EResponseCode responseCode) {
        super(responseCode.getCode(), responseCode.getDescribe());
    }
}
