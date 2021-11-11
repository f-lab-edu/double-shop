package com.project.doubleshop.domain.exception;

/**
 * UnauthenticatedMemberException
 * 로그인하지 않은 사용자가 특정 페이지에 접근하려 할 때 발생하는 예외
 */
public class UnauthenticatedMemberException extends RuntimeException {

    public UnauthenticatedMemberException() {
    }

    public UnauthenticatedMemberException(String message) {
        super(message);
    }

}
