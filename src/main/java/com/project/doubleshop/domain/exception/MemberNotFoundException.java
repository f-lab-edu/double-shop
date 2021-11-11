package com.project.doubleshop.domain.exception;

/**
 * MemberNotFoundException
 * 로그인 시 사용자가 입력한 회원정보(아이디와 비밀번호)가 존재하지 않을 때 발생하는 예외
 */
public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException() {
    }

    public MemberNotFoundException(String message) {
        super(message);
    }

}
