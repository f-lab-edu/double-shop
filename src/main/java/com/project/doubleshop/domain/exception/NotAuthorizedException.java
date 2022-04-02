package com.project.doubleshop.domain.exception;

public class NotAuthorizedException extends ServiceException {
    public NotAuthorizedException(String message, Integer statusCode) {
        super(message, statusCode);
    }
}
