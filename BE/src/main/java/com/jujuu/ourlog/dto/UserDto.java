package com.jujuu.ourlog.dto;

import com.jujuu.ourlog.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String name;

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
