package com.challenge.ivms;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.SneakyThrows;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RSAKeySupplier {

    @SneakyThrows(NoSuchAlgorithmException.class)
    public static RSAKey getKey(Integer keySize, KeyUse keyUse, Algorithm keyAlg, String kid) {

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(keySize);

        KeyPair kp = generator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();

        return new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyUse(keyUse)
                .algorithm(keyAlg)
                .keyID(kid)
                .build();
    }

}
