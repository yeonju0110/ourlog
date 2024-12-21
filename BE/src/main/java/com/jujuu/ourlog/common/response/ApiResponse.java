package com.jujuu.ourlog.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private final int status;
    private String errorCode;
    private final String message;
    private T data;

    // 성공 응답 생성자
    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // 실패 응답 생성자
    public ApiResponse(int status, String message, String errorCode) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
    }
}
