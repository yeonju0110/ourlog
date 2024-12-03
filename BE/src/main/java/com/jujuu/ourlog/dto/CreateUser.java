package com.jujuu.ourlog.dto;

import com.jujuu.ourlog.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CreateUser {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        @NotNull
        @Size(min = 3, max = 20, message = "ID 길이는 3~20 사이여야 합니다")
        private String userId;

        @NotNull
        @Size(min = 3, max = 20, message = "닉네임 길이는 3~20 사이여야 합니다")
        private String nickname;

        @NotNull
        @Email(message = "이메일 형식이 올바르지 않습니다")
        private String email;

        @NotNull
        @Size(min = 8, max = 20, message = "비밀번호 길이는 8~20 사이여야 합니다")
        private String password;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private String userId;

        public static Response fromEntity(User user) {
            return Response.builder()
                    .userId(user.getUserId())
                    .build();
        }
    }
}
