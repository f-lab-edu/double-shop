package com.project.doubleshop.domain.member.service;

import com.project.doubleshop.web.member.dto.SmsVerificationRequestDto;

import javax.servlet.http.HttpSession;
import java.util.Random;

// 회원가입 시 사용자가 입력한 휴대폰 번호로 인증 번호 전송
public interface SmsVerificationService {

    // 인증 번호를 전송하고, 발송 데이터를 세션에 저장
    public void sendMessage(String phoneNum, HttpSession session);

    // 사용자가 입력한 인증 번호와 세션에 저장된 인증 번호 비교
    public void verifyMessage(SmsVerificationRequestDto requestDto, HttpSession session);

    // 메시지 내용 생성
    public String makeMessageContent(String verificationNumber);

    // 인증 번호 생성 (100000 ~ 999999)
    public static String makeAuthNumber() {
        Random random = new Random();

        return String.valueOf(100000 + random.nextInt(900000));
    }

}
