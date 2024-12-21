package com.jujuu.ourlog.common.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTUtil {

    private final Key key;
    private final long expirationTime;
    private static final int KEY_SIZE = 32;

    public JWTUtil(
            @Value("${encryption.jwt.secret-key}") String secretKey,
            @Value("${encryption.jwt.expiration-time}") long expirationTime) {
        if (secretKey == null || secretKey.length() != KEY_SIZE) {
            throw new IllegalArgumentException("JWT키는 반드시 32자 이상여야 합니다.");
        }
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.expirationTime = expirationTime;
    }

    /**
     * JWT 생성 메서드
     *
     * @param userId 사용자 로그인 ID
     * @return 생성된 JWT
     */
    public String generateToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date()) // 생성 시간
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // 만료 시간
                .signWith(key, SignatureAlgorithm.HS256) // 서명 알고리즘 및 키 설정
                .compact();
    }

    /**
     * JWT 검증 메서드
     *
     * @param token 검증할 JWT
     * @return 검증된 Claims 객체
     */
    public Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key) // 서명 키 설정
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
    public String extractUserId(String token) {
        return validateToken(token).getSubject();
    }
}
