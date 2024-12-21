package com.jujuu.ourlog.auth;

import io.jsonwebtoken.Claims;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
@RequiredArgsConstructor
public class JWTInterceptor implements HandlerInterceptor {

    private final JWTUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler)
            throws Exception {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing or invalid Authorization header");
            return false;
        }

        String token = authorizationHeader.substring(7);

        try {
            Claims claims = jwtUtil.validateToken(token);
            request.setAttribute("userId", claims.getSubject()); // 유저 정보 저장
            return true;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid token");
            return false;
        }
    }
}
