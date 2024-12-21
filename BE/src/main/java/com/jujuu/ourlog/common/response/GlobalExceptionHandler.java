package com.jujuu.ourlog.common.response;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OurlogException.class)
    public ApiResponseEntity<Object> handleOurlogException(OurlogException ex) {
        OurlogErrorCode errorCode = ex.getOurlogErrorCode();

        return ApiResponseEntity.failure(
                errorCode.getStatus(),
                errorCode.getMessage(),
                errorCode.getErrorCode()
        );
    }
}
