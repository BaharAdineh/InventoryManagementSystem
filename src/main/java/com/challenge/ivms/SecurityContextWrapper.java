package com.challenge.ivms;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextWrapper {

    public UserPrincipal getPrincipal() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new AuthenticationCredentialsNotFoundException("Authentication not found!");
        }
        return (UserPrincipal) authentication.getPrincipal();
    }

}
