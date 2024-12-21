package com.jujuu.ourlog.common.auth;

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * AES-256 암호화 및 복호화를 위한 유틸리티 클래스 AES/CBC/PKCS5Padding 알고리즘을 사용하여 문자열 암호화 및 복호화 기능 제공
 */
@Component
public class AES256Util {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding"; // 알고리즘, 모드 및 패딩 방식
    private static final int KEY_SIZE = 32; // 256비트 키는 32자의 문자열이 필요함
    private static final int IV_SIZE = 16;  // AES 블록 크기 (16바이트)로 초기화 벡터 크기 설정

    private final SecretKeySpec secretKey;

    /**
     * AES256Util 클래스의 생성자. 비밀 키로 초기화
     *
     * @param key 32자의 비밀 키
     * @throws IllegalArgumentException 키 길이가 32자가 아닌 경우 예외 발생
     */
    public AES256Util(@Value("${encryption.aes.key}") String key) {
        if (key == null || key.length() != KEY_SIZE) {
            throw new IllegalArgumentException("AES-256을 사용하려면 키는 반드시 32자여야 합니다.");
        }
        this.secretKey = new SecretKeySpec(key.getBytes(), "AES");
    }

    /**
     * 평문(PlainText)을 암호화합니다.
     *
     * @param plainText 암호화할 평문
     * @return Base64로 인코딩된 암호문
     * @throws Exception 암호화 중 오류가 발생한 경우 예외 발생
     */
    public String encrypt(String plainText) throws Exception {
        // 암호화 모드로 Cipher 초기화
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // 랜덤 초기화 벡터(IV) 생성
        byte[] ivBytes = generateRandomIV();
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        // 평문 암호화
        byte[] encrypted = cipher.doFinal(plainText.getBytes());

        // IV와 암호문을 결합한 후 Base64로 인코딩
        byte[] ivAndEncrypted = combineIVAndCipher(ivBytes, encrypted);
        return Base64.getEncoder().encodeToString(ivAndEncrypted);
    }

    /**
     * Base64로 인코딩된 암호문(CipherText)을 복호화합니다.
     *
     * @param cipherText 복호화할 Base64로 인코딩된 암호문
     * @return 복호화된 평문
     * @throws Exception 복호화 중 오류가 발생한 경우 예외 발생
     */
    public String decrypt(String cipherText) throws Exception {
        // Base64 입력값 디코딩
        byte[] ivAndEncrypted = Base64.getDecoder().decode(cipherText);

        // IV와 암호문 분리
        byte[] ivBytes = extractIV(ivAndEncrypted);
        byte[] encryptedBytes = extractCipher(ivAndEncrypted);

        // 복호화 모드로 Cipher 초기화
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(ivBytes));

        // 암호문 복호화
        byte[] decrypted = cipher.doFinal(encryptedBytes);
        return new String(decrypted);
    }

    /**
     * 랜덤 16바이트 초기화 벡터(IV)를 생성합니다
     *
     * @return 생성된 랜덤 IV
     */
    private byte[] generateRandomIV() {
        byte[] ivBytes = new byte[IV_SIZE];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(ivBytes);
        return ivBytes;
    }

    /**
     * IV와 암호문을 하나의 바이트 배열로 결합합니다.
     *
     * @param iv     초기화 벡터(IV)
     * @param cipher 암호화된 데이터
     * @return 결합된 IV와 암호문
     */
    private byte[] combineIVAndCipher(byte[] iv, byte[] cipher) {
        byte[] combined = new byte[IV_SIZE + cipher.length];
        System.arraycopy(iv, 0, combined, 0, IV_SIZE);
        System.arraycopy(cipher, 0, combined, IV_SIZE, cipher.length);
        return combined;
    }

    /**
     * 결합된 IV와 암호문에서 IV를 추출합니다.
     *
     * @param ivAndCipher 결합된 IV와 암호문
     * @return 추출된 IV
     */
    private byte[] extractIV(byte[] ivAndCipher) {
        byte[] iv = new byte[IV_SIZE];
        System.arraycopy(ivAndCipher, 0, iv, 0, IV_SIZE);
        return iv;
    }

    /**
     * 결합된 IV와 암호문에서 암호문을 추출합니다.
     *
     * @param ivAndCipher 결합된 IV와 암호문
     * @return 추출된 암호문
     */
    private byte[] extractCipher(byte[] ivAndCipher) {
        byte[] cipher = new byte[ivAndCipher.length - IV_SIZE];
        System.arraycopy(ivAndCipher, IV_SIZE, cipher, 0, cipher.length);
        return cipher;
    }
}
