package com.challenge.ivms.config;

import com.challenge.ivms.JwsParser;
import com.challenge.ivms.TokenAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

import jakarta.servlet.http.HttpServletResponse;
import org.owasp.encoder.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JWKSource<SecurityContext> jwkSource;

    @Value("${security.jws.max-clock-skew-seconds}")
    private int maxClockSkewInSeconds;

    @Bean
    @Order(1)
    public SecurityFilterChain jwtSecurityFilterChain(HttpSecurity http) throws Exception {
        // nosemgrep semgrep_rulesets.java.spring.security.audit.spring-csrf-disabled.spring-csrf-disabled
        http.securityMatcher("/business-users/**")
            .csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            // Here TokenAuthenticationFilter is applied only to paths listed below.
            // Effectively, a 'whitelist' is in operation, all other paths are open.
            .addFilterBefore(new TokenAuthenticationFilter(objectMapper, (request, response, authException) ->
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Encode.forHtmlContent(authException.getMessage())),
                    new JwsParser(new DefaultJWTProcessor<>(), jwkSource, maxClockSkewInSeconds)),
                BasicAuthenticationFilter.class)
            .headers().contentSecurityPolicy("default-src 'none'; frame-ancestors 'none'")
            .and()
            .xssProtection();

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain actuatorSecurityFilterChain(HttpSecurity http) throws Exception {
        // nosemgrep semgrep_rulesets.java.spring.security.audit.spring-csrf-disabled.spring-csrf-disabled
        http.securityMatcher(EndpointRequest.toAnyEndpoint())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(EndpointRequest.to(HealthEndpoint.class, InfoEndpoint.class)).permitAll()
                .anyRequest().hasRole("ACTUATOR"))
            .csrf().disable()
            .httpBasic(withDefaults())
            .headers().contentSecurityPolicy("default-src 'none'; frame-ancestors 'none'")
            .and()
            .xssProtection();

        return http.build();
    }
}
