package com.project.doubleshop.exception;

import org.springframework.http.HttpStatus;

/**
 * NotFoundException
 * 로그인 시 사용자가 입력한 회원정보(아이디와 비밀번호)가 존재하지 않을 때 발생하는 예외
 */
public class NotFoundException extends ServiceException {

    public NotFoundException(String message) {
        super(message);
        super.statusCode = HttpStatus.NOT_FOUND.value();
    }
}
