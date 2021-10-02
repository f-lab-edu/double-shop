package com.project.doubleshop.domain.member.service;

import com.project.doubleshop.domain.utils.verification.email.EmailContentTemplate;
import com.project.doubleshop.web.member.dto.EmailVerificationRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Random;

// 비밀번호 찾기를 요청하는 사용자의 이메일 주소로 인증 이메일 전송
@AllArgsConstructor
@Service
public class EmailVerificationService {

    public static final String TITLE = "[Double Shop] 인증번호 안내 메일";
    public static final String SENDER_ADDRESS = "carpedm082@gmail.com";
    public static final int LIMIT_TIME = 60 * 10;

    private final JavaMailSender mailSender;

    // 인증 번호를 전송하고, 발송 데이터를 세션에 저장
    public void sendEmail(String email, HttpSession session) {
        String authNum = makeAuthNumber();

        String content = makeEmailContent(authNum);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setFrom(SENDER_ADDRESS);
        message.setSubject(TITLE);
        message.setText(content);
        mailSender.send(message);

        session.setAttribute(email, authNum);
        session.setMaxInactiveInterval(LIMIT_TIME);
    }

    // 인증 번호 생성 (100000 ~ 999999)
    public static String makeAuthNumber() {
        Random random = new Random();

        return String.valueOf(100000 + random.nextInt(900000));
    }

    // 이메일 내용 생성
    public String makeEmailContent(String verificationNumber) {
        EmailContentTemplate content = new EmailContentTemplate();

        content.setVerificationNum(verificationNumber);

        return content.parse();
    }

    // 인증 번호 비교
    public boolean verifyEmail(EmailVerificationRequestDto requestDto, HttpSession session) {
        String email = requestDto.getEmail();
        String verificationNum = requestDto.getVerificationNum();

        boolean result = session.getAttribute(email).equals(verificationNum);

        session.removeAttribute(requestDto.getEmail());

        return result;
    }

}
