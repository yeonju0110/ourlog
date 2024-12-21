package com.jujuu.ourlog.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponseEntity<T> extends ResponseEntity<ApiResponse<T>> {

    // 생성자
    public ApiResponseEntity(ApiResponse<T> body, HttpStatus status) {
        super(body, status);
    }

    // 성공 응답 팩토리 메서드
    public static <T> ApiResponseEntity<T> success(HttpStatus status, String message, T data) {
        ApiResponse<T> response = new ApiResponse<>(status.value(), message, data);
        return new ApiResponseEntity<>(response, status);
    }

    // 실패 응답 팩토리 메서드
    public static ApiResponseEntity<Object> failure(HttpStatus status, String message, String errorCode) {
        ApiResponse<Object> response = new ApiResponse<>(status.value(), message, errorCode);
        return new ApiResponseEntity<>(response, status);
    }
}
