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
    public static <T> Result<T> success(T data){
        return new Result<T>(20000,data,"ok");
    }

    public static <T> Result<T> error(ErrorCode errorCode){
        return new Result<T>(errorCode);

    }
}
