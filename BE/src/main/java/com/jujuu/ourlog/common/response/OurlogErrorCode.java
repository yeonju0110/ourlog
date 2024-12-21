package com.jujuu.ourlog.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OurlogErrorCode {

    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "4001", "유효하지 않은 입력값입니다."),
    LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "4002", "로그인 정보가 올바르지 않습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "4003", "접근 권한이 없습니다."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "4004", "요청한 리소스를 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "4005", "허용되지 않은 HTTP 메서드입니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "5000", "서버 내부 오류가 발생했습니다."),
    EXTERNAL_API_ERROR(HttpStatus.BAD_GATEWAY, "5001", "외부 API 호출 중 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String errorCode; // 커스텀 에러 코드
    private final String message;
}
