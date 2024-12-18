package com.jujuu.ourlog.controller;

import com.jujuu.ourlog.dto.LoginDto;
import com.jujuu.ourlog.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginDto.Response login(
            @Valid @RequestBody LoginDto.Request request
    ) throws Exception {
        log.info("POST /login HTTP/1.1 request: {}", request);

        return authService.login(request);
    }
}
