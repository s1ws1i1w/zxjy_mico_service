package com.bs.union.midware.exception;

import com.bs.union.midware.responsecode.BaseResponse;
import com.bs.union.midware.responsecode.RsCode;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

/**
 * 逻辑错误异常类 用于 Assert
 *
 * @author qigaoxin
 * @date 2020年8月5日 11点41分
 */
@Data
public class LogicException extends RuntimeException {

    private BaseResponse baseResponse = RsCode.CommonRsCode.SYSTEM_PARAM_ERROR;

    public LogicException(BaseResponse baseResponse) {
        super(baseResponse.getMsg());
        if (ObjectUtils.isNotEmpty(baseResponse)) {
            this.baseResponse = baseResponse;
        }
    }
}
