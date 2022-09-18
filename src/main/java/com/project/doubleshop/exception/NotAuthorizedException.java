package com.project.doubleshop.exception;

public class NotAuthorizedException extends ServiceException {
    public NotAuthorizedException(String message, Integer statusCode) {
        super(message, statusCode);
    }
}
