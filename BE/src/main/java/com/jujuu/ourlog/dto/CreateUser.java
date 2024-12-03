package com.jujuu.ourlog.dto;

import com.jujuu.ourlog.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class CreateUser {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request {
        @NotNull
        @Size(min = 3, max = 20, message = "name length must be between 3 and 20")
        private String name;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private Long id;
        private String name;

        public static Response fromEntity(User user) {
            return Response.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .build();
        }
    }
}
