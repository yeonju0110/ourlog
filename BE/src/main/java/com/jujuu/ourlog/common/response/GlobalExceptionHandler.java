package com.jujuu.ourlog.common.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OurlogException.class)
    public ApiResponseEntity<Object> handleOurlogException(OurlogException ex) {
        OurlogErrorCode errorCode = ex.getOurlogErrorCode();

        if (ex.getLogMessage() != null) {
            log.warn(ex.getLogMessage());
        }

        return ApiResponseEntity.failure(
                errorCode.getStatus(),
                errorCode.getMessage(),
                errorCode.getErrorCode()
        );
    }

    @ExceptionHandler(Exception.class)
    public ApiResponseEntity<Object> handleGenericException(Exception ex) {
        log.error("Unhandled exception occurred: {}", ex.getMessage(), ex);

        return ApiResponseEntity.failure(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred.",
                "5000"
        );
    }

}
