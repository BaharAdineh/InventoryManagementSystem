package com.challenge.ivms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class TokenAuthenticationFilter extends GenericFilterBean {

    public static final String AUTH_HEADER_NAME = "x-auth-user";
    private static final String UNAUTHORIZED_ACCESS = "User is unauthorized to access the application.";

    private final ObjectMapper objectMapper;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final JwsParser jwsParser;
    private UserPrincipalValidator userPrincipalValidator;

    public TokenAuthenticationFilter(final ObjectMapper objectMapper, AuthenticationEntryPoint authenticationEntryPoint, JwsParser jwsParser) {
        this.objectMapper = objectMapper;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.jwsParser = jwsParser;
        this.userPrincipalValidator = userPrincipalValidator;
    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain)
            throws IOException, ServletException {
        try {
            final String encryptedToken = getEncryptedToken((HttpServletRequest) servletRequest);
            final JWTClaimsSet jwtClaimsSet = jwsParser.parse(encryptedToken);
            if (log.isDebugEnabled()) {
                log.debug("Processing token {} with claim set {}", encryptedToken, jwtClaimsSet.getClaims());
            }
            final UserPrincipal userPrincipal = objectMapper.convertValue(jwtClaimsSet.getClaims(), UserPrincipal.class);

            userPrincipalValidator.validate(userPrincipal);

            userPrincipal.setToken(encryptedToken);
            SecurityContextHolder.getContext().setAuthentication(new TokenBasedAuthentication(encryptedToken, userPrincipal, new ArrayList<>()));
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (AuthenticationException | IllegalArgumentException | JsonProcessingException | ParseException | JOSEException | BadJOSEException | InvalidPrincipalException e) {
            log.error("Error in token authentication filter.", e);
            authenticationEntryPoint.commence((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse,
                    new InvalidTokenException(UNAUTHORIZED_ACCESS, e));
        }
    }

    private String getEncryptedToken(HttpServletRequest request) {
        String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            return token;
        }
        throw new AuthenticationCredentialsNotFoundException("Missing required HTTP header: " + AUTH_HEADER_NAME);
    }

    public static class InvalidTokenException extends AuthenticationException {
        InvalidTokenException(final String msg, final Throwable e) {
            super(msg, e);
        }
    }
}
