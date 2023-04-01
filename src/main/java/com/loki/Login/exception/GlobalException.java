package com.loki.Login.exception;

import com.loki.Login.common.ErrorCode;
import com.loki.Login.common.Result;
import com.loki.Login.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常
 *
 * @author Arthurocky
 * @date 2023/04/01
 */
@RestControllerAdvice
@Slf4j
public class GlobalException extends RuntimeException{

    @ExceptionHandler(BusinessException.class)
    public Result businessExceptionHandler(BusinessException e) {
        log.error("businessException: " + e.getMessage(), e);
        return R.error(e.getCode(), e.getMessage(), e.getDescription());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException", e);
        return R.error(ErrorCode.SYSTEM_ERROR, e.getMessage(), "");
    }
}
