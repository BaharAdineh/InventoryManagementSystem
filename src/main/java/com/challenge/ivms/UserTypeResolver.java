package com.challenge.ivms;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserTypeResolver {

    public boolean isEOTPUser(UserPrincipal userPrincipal) {
        return userPrincipal != null
                && StringUtils.hasText(userPrincipal.getAuthUserId())
                && UserPrincipal.EOTP_AUTH_USER_TYPE.equalsIgnoreCase(userPrincipal.getAuthUserType());
    }

    public boolean isOKPassUser(UserPrincipal userPrincipal) {
        return userPrincipal != null
                && StringUtils.hasText(userPrincipal.getSiebelUserRelationId()) && StringUtils.hasText(userPrincipal.getSiebelCustomerRelationId())
                && StringUtils.hasText(userPrincipal.getEdoAgreementId()) && StringUtils.hasText(userPrincipal.getEdoUserId())
                && UserPrincipal.OKPASS_AUTH_USER_TYPE.equalsIgnoreCase(userPrincipal.getAuthUserType());
    }

    public UserType getUserType(UserPrincipal userPrincipal) {
        if (isEOTPUser(userPrincipal)) {
            return UserType.EOTP;
        } else if (isOKPassUser(userPrincipal)) {
            return UserType.OKPASS;
        } else {
            return UserType.UNKNOWN;
        }
    }

}
