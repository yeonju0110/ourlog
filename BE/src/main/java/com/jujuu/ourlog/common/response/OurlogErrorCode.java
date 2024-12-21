package com.jujuu.ourlog.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OurlogErrorCode {

    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "4004", "요청한 리소스를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "5000", "서버에 오류가 발생했습니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "5001", "올바르지 않은 입력값입니다.");

    private final HttpStatus status;
    private final String errorCode; // 커스텀 에러 코드
    private final String message;
}
