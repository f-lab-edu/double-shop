package com.project.doubleshop.domain.member.service;

import com.project.doubleshop.web.member.dto.EmailVerificationRequestDto;

import javax.servlet.http.HttpSession;
import java.util.Random;

// 비밀번호 찾기를 요청하는 사용자의 이메일 주소로 인증 이메일 전송
public interface EmailVerificationService {

    // 인증 번호를 전송하고, 발송 데이터를 세션에 저장
    public void sendEmail(String email, HttpSession session);

    // 인증 번호 비교
    public boolean verifyEmail(EmailVerificationRequestDto requestDto, HttpSession session);

    // 이메일 내용 생성
    public String makeMessageContent(String verificationNumber);

    // 인증 번호 생성 (100000 ~ 999999)
    public static String makeAuthNumber() {
        Random random = new Random();

        return String.valueOf(100000 + random.nextInt(900000));
    }

}
