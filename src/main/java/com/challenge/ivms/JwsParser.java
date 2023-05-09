package com.challenge.ivms;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.BadJWTException;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTClaimsVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class JwsParser {

    private final JWSAlgorithm jwsAlgorithm;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwsParser.class);

    private final ConfigurableJWTProcessor<SecurityContext> jwtProcessor;
    private final JWKSource<SecurityContext> jwkSource;

    public JwsParser(ConfigurableJWTProcessor<SecurityContext> processor, JWKSource<SecurityContext> jwkSource,
                     int maxClockSkewSeconds) {
        this.jwtProcessor = processor;
        this.jwkSource = jwkSource;
        this.jwsAlgorithm = JWSAlgorithm.RS512;
        initializeProcessor(maxClockSkewSeconds);
    }

    public JwsParser(ConfigurableJWTProcessor<SecurityContext> processor, JWKSource<SecurityContext> jwkSource,
                     int maxClockSkewSeconds, JWSAlgorithm jwsAlgorithm) {
        this.jwtProcessor = processor;
        this.jwkSource = jwkSource;
        this.jwsAlgorithm = jwsAlgorithm;
        initializeProcessor(maxClockSkewSeconds);
    }

    public JWTClaimsSet parse(final String authUserHeader) throws ParseException, JOSEException, BadJOSEException {
        long timestamp = System.currentTimeMillis();
        final JWTClaimsSet claimsSet;
        try {
            claimsSet = jwtProcessor.process(authUserHeader, null);
        } catch (final BadJWTException jwtException) {
            LOGGER.error("JWT token is invalid: {}. Token is: {}. Timestamp right before evaluation: {}", jwtException.getMessage(), authUserHeader, ZonedDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()));
            throw jwtException;
        }
        LOGGER.debug("Received authenticated user: {}", claimsSet.toJSONObject());
        return claimsSet;
    }

    private void initializeProcessor(int maxClockSkewSeconds) {
        final JWSKeySelector<SecurityContext> keySelector = new JWSVerificationKeySelector<>(this.jwsAlgorithm, this.jwkSource);
        this.jwtProcessor.setJWSKeySelector(keySelector);
        final var verifier = new DefaultJWTClaimsVerifier<>(null, null, null, null);
        verifier.setMaxClockSkew(maxClockSkewSeconds);
        this.jwtProcessor.setJWTClaimsSetVerifier(verifier);
    }
}
