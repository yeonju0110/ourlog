package com.jujuu.ourlog.service;

import com.jujuu.ourlog.common.auth.AES256Util;
import com.jujuu.ourlog.dto.CreateUser;
import com.jujuu.ourlog.dto.UserDto;
import com.jujuu.ourlog.entity.User;
import com.jujuu.ourlog.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final AES256Util aes256Util;

    @Transactional
    public CreateUser.Response createUser(CreateUser.Request request) throws Exception {
        // TODO: 유효성 검증 에러 반환

        User user = User.builder()
                .userId(request.getUserId())
                .nickname(request.getNickname())
                .email(request.getEmail())
                .password(encryptPassword(request.getPassword()))
                .profileMedia(null)
                .build();

        userRepository.save(user);
        return CreateUser.Response.fromEntity(user);
    }

    private String encryptPassword(String rawPassword) throws Exception {
        return aes256Util.encrypt(rawPassword);
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }
}
