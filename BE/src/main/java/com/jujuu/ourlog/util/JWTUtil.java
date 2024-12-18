package com.jujuu.ourlog.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JWTUtil {
    private static final String SECRET_KEY = "12345678901234567890123456789012"; // TODO: 수정 - 최소 32자 이상의 시크릿 키
    private static final long EXPIRATION_TIME = 3600000; // 1시간

    // HMAC-SHA 키 생성
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    /**
     * JWT 생성 메서드
     *
     * @param userId 사용자 로그인 ID
     * @return 생성된 JWT
     */
    public static String generateToken(String userId) {
        return Jwts.builder()
                .setSubject(userId) // 토큰의 주체 설정
                .setIssuedAt(new Date()) // 생성 시간
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 만료 시간
                .signWith(KEY, SignatureAlgorithm.HS256) // 서명 알고리즘 및 키 설정
                .compact();
    }

    /**
     * JWT 검증 메서드
     *
     * @param token 검증할 JWT
     * @return 검증된 Claims 객체
     */
    public static Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY) // 서명 키 설정
                .build()
                .parseClaimsJws(token) // 토큰 검증
                .getBody(); // Claims 반환
    }

    /**
     * JWT에서 사용자 로그인 ID 추출
     *
     * @param token JWT
     * @return 추출된 사용자 로그인 ID
     */
    public static String extractUserIdId(String token) {
        return validateToken(token).getSubject();
    }
}
