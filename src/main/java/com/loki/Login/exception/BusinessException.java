package com.loki.Login.exception;

import com.loki.Login.common.ErrorCode;

/**
 * 业务异常
 *
 * @author Arthurocky
 * @date 2023/04/01
 */
public class BusinessException extends RuntimeException{

    private final int code;

    private final String description;


    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BusinessException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }



}
