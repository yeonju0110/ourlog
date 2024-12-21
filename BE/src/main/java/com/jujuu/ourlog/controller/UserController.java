package com.jujuu.ourlog.controller;

import com.jujuu.ourlog.common.response.ApiResponseEntity;
import com.jujuu.ourlog.dto.CreateUser;
import com.jujuu.ourlog.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ApiResponseEntity<CreateUser.Response> createUser(
            @Valid @RequestBody CreateUser.Request request
    ) throws Exception {
        log.info("request: {}", request);

        CreateUser.Response response = userService.createUser(request);
        return ApiResponseEntity.success(HttpStatus.CREATED, "User created successfully", response);
    }
}
