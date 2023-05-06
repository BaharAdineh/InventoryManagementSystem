package com.challenge.ivms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class JwtDecoderConfig {

    @Value("${jwt.public.key}")
    private String publicKey;

    @Bean
    public JwtDecoder jwtDecoder() {
        // Replace publicKey with your actual public key
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }
}
