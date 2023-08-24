package com.challenge.ivms.userservice.security.jwt;

import java.security.SecureRandom;
import java.util.Base64;

public class SecretKeyGenerator {

    public static void main(String[] args) {
        generateSecretKey();
    }

    public static void generateSecretKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[64]; // Adjust the size based on your needs
        secureRandom.nextBytes(keyBytes);
        String secretKey = Base64.getEncoder().encodeToString(keyBytes);

        System.out.println("Generated Secret Key: " + secretKey);
    }
}

