package com.project.doubleshop.domain.member.service;

import com.project.doubleshop.web.member.dto.SmsVerificationRequestDto;

import javax.servlet.http.HttpSession;
import java.util.Random;

public interface SmsVerificationService {

    public void sendMessage(String phoneNum, HttpSession session);

    public void verifyMessage(SmsVerificationRequestDto requestDto, HttpSession session);

    public String makeMessageContent(String verificationNumber);

    public static String makeAuthNumber() {
        Random random = new Random();

        return String.valueOf(100000 + random.nextInt(900000));
    }

}
