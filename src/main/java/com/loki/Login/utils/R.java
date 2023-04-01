package com.loki.Login.utils;

import com.loki.Login.common.ErrorCode;
import com.loki.Login.common.Result;

/**
 * 通用返回类型工具类
 *
 * @author Arthurocky
 * @date 2023/04/01
 */
public class R {


    /**
     * 成功
     *
     * @param data 数据
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> success(T data){
        return new Result<T>(20000,data,"ok");
    }

    /**
     * 成功
     *
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> success(){
        return new Result<T>(20000,null,"ok");
    }

    /**
     * 成功
     *
     * @param message 消息
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> success(String message){
        return new Result<T>(20000,null,"message");
    }

    /**
     * 错误
     *
     * @param errorCode 错误代码
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> error(ErrorCode errorCode){
        return new Result<T>(errorCode);
    }


    /**
     * 错误
     *
     * @param code        代码
     * @param message     消息
     * @param description 描述

     */
    public static <T> Result<T> error(int code, String message, String description) {
        return new Result<T>(code, null, message, description);
    }

    /**
     * 失败
     *
     * @param errorCode
     * @return
     */
    public static <T> Result<T> error(ErrorCode errorCode, String message, String description) {
        return new Result<T>(errorCode.getCode(), null, message, description);
    }



    /**
     * 失败
     *
     * @param errorCode
     * @return
     */
    public static <T> Result<T> error(ErrorCode errorCode, String description) {
        return new Result<T>(errorCode.getCode(),null,errorCode.getMessage() , description);
    }

}
