package com.jujuu.ourlog.common.config;

import com.jujuu.ourlog.common.auth.JWTInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final JWTInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/api/v1/**") // 보호할 경로 지정
                .excludePathPatterns(
                        "/api/v1/auth/login", // 로그인 경로 제외
                        "/api/v1/users" // 회원가입 경로 제외
                );
    }
}
