package com.jujuu.ourlog.common.auth;

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
            // JWT 검증
            Claims claims = jwtUtil.validateToken(token);

            // 유저 ID를 ThreadLocal에 저장
            UserContext.setUserId(claims.getSubject());

            return true;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid token");
            return false;
        }
    }

    @Override
    public void afterCompletion(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response,
                                @Nonnull Object handler, @Nonnull Exception ex) {
        UserContext.clear(); // 요청 처리 후 ThreadLocal 값 삭제
    }
}
