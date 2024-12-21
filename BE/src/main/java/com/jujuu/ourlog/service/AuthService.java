package com.jujuu.ourlog.service;

import com.jujuu.ourlog.common.auth.AES256Util;
import com.jujuu.ourlog.common.auth.JWTUtil;
import com.jujuu.ourlog.dto.LoginDto;
import com.jujuu.ourlog.entity.User;
import com.jujuu.ourlog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    private final AES256Util aes256Util;

    private final JWTUtil jwtUtil;

    public LoginDto.Response login(LoginDto.Request request) throws Exception {

        // DB에서 사용자를 검증
        User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid userId"));

        // PW 검증
        if (!request.getPassword().equals(aes256Util.decrypt(user.getPassword()))) {
            throw new IllegalArgumentException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getUserId());

        return LoginDto.Response.of(token);
    }
}
