package com.challenge.ivms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserIdentifierProducer {

    private final UserTypeResolver userTypeResolver;

    public String getUserIdentifier(UserPrincipal principal) {

        if (userTypeResolver.isOKPassUser(principal)) {
            return principal.getEdoAgreementId() + "_" + principal.getEdoUserId();
        }

        return principal.getAuthUserId();
    }
}
