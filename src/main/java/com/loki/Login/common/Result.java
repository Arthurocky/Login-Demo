package com.loki.Login.common;

import lombok.Data;

/**
 * 通用反应类型
 *
 * @author Arthurocky
 * @date 2023/04/01
 */
@Data
public class Result<T> {

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回泛型
     */
    private T data;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 描述信息
     */
    private String description;

    public Result(int code, T data, String message, String description)
    {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public Result()
    {
    }

    public Result(int code, T data, String message) {
        this(code, data, message, "");
    }

    public Result(int code, T data) {
        this(code, data, "", "");
    }

    public Result(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage(), errorCode.getDescription());
    }
}
