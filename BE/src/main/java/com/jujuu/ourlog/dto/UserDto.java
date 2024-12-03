package com.jujuu.ourlog.dto;

import com.jujuu.ourlog.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String nickname;

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .build();
    }
}
