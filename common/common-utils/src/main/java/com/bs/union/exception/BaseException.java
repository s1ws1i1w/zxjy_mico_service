package com.bs.union.exception;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * @author ludezh
 * @company Information Technology
 * @description 用于
 * @since 2017/12/11
 */
public class BaseException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1884511028503884702L;
    private String code;
    private String description;
    private String classId;
    /**
     * 是否入库记录
     */
    private boolean isRecord = false;

    public BaseException(String code, String description, boolean isRecord, Throwable cause) {
        super(description, cause);
        this.code = code;
        this.description = description;
        this.isRecord = isRecord;
        if (null != cause) {
            this.classId = getClassIdName(cause.getStackTrace()[0].getClassName());
        }
    }

    public BaseException(String code, String description, boolean isRecord) {
        this(code, description, isRecord, null);
    }

    public BaseException(String code, String description) {
        this(code, description, false);
    }

    public BaseException(String description, boolean isRecord) {
        this(null, description, isRecord);
    }

    public BaseException(String description) {
        this(description, false);
    }

    public BaseException(String description, boolean isRecord, Throwable cause) {
        this(null, description, isRecord, cause);
    }

    public BaseException(String description, Throwable cause) {
        this(description, false, cause);
    }

    public BaseException(Throwable cause) {
        this(null, cause);
    }

    /**
     * 根据完整类名(含包名)截取类名
     *
     * @param className
     * @return
     */
    private static String getClassIdName(String className) {
        int index = className.lastIndexOf(".");
        if (index < 0) {
            return className;
        }
        return className.substring(index + 1);
    }


    public boolean getIsRecord() {
        return this.isRecord;
    }

    public void setIsRecord(boolean isRecord) {
        this.isRecord = isRecord;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
