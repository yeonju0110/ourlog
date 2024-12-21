package com.jujuu.ourlog.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class LoginDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        @NotNull
        @Size(min = 3, max = 20, message = "ID 길이는 3~20 사이여야 합니다")
        private String userId;

        @NotNull
        @Size(min = 8, max = 20, message = "비밀번호 길이는 8~20 사이여야 합니다")
        private String password;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private String accessToken;

        public static LoginDto.Response of(String accessToken) {
            return Response.builder()
                    .accessToken(accessToken)
                    .build();
        }
    }
}
