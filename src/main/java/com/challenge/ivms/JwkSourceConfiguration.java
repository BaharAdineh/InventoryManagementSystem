package com.challenge.ivms;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
public class JwkSourceConfiguration {

    @Bean
    @Profile({"integration"})
    public JWKSource<SecurityContext> localKeySource()  {
        return new ImmutableJWKSet<>(new JWKSet(RSAKeySupplier.getKey(2048, KeyUse.SIGNATURE, JWSAlgorithm.RS512, "rcc-edge-router-v1")));
    }

    @Bean
    @Profile({"!integration"})
    public JWKSource<SecurityContext> remoteKeySource(@Value("${security.jws.connection-timeout}") int connectionTimeout,
                                                      @Value("${security.jws.read-timeout}") int readTimeout,
                                                      @Value("${security.jws.url}") String jwkUrl) {
        final DefaultResourceRetriever resourceRetriever = new DefaultResourceRetriever(connectionTimeout, readTimeout);
        try {
            return new RemoteJWKSet<>(new URL(jwkUrl), resourceRetriever);
        } catch (final MalformedURLException e) {
            throw new IllegalStateException("Unable to access remote jwk server endpoint", e);
        }
    }
}
