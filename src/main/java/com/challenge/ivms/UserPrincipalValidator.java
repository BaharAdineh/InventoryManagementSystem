package com.challenge.ivms;

public interface UserPrincipalValidator {

    void validate(UserPrincipal userPrincipal) throws InvalidPrincipalException;

}
