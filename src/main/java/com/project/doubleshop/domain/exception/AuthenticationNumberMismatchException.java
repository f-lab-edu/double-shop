package com.project.doubleshop.domain.exception;

public class AuthenticationNumberMismatchException extends RuntimeException {

    public AuthenticationNumberMismatchException() {
    }

    public AuthenticationNumberMismatchException(String message) {
        super(message);
    }

}
