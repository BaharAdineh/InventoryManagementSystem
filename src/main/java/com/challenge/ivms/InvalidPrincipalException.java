package com.challenge.ivms;

public class InvalidPrincipalException extends Exception {

    public InvalidPrincipalException() {
    }

    public InvalidPrincipalException(String message) {
        super(message);
    }

    public InvalidPrincipalException(String message, Throwable cause) {
        super(message, cause);
    }

}
