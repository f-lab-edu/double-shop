package com.project.doubleshop.exception;

// TODO 사용가능성이 낮은 예외 해당 예외 검토 후 수정 혹은 삭제
public class AuthenticationNumberMismatchException extends RuntimeException {

    public AuthenticationNumberMismatchException() {
    }

    public AuthenticationNumberMismatchException(String message) {
        super(message);
    }

}
