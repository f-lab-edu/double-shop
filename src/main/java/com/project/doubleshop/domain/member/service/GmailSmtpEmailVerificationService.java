package com.project.doubleshop.domain.member.service;

import com.project.doubleshop.domain.utils.verification.MessageContentTemplate;
import com.project.doubleshop.web.member.dto.EmailVerificationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class GmailSmtpEmailVerificationService implements EmailVerificationService {

    private static String senderAddress;
    private static final int LIMIT_TIME = 60 * 10;

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    public void setSenderAddress(String address) {
        senderAddress = address;
    }

    @Override
    public void sendEmail(String email, HttpSession session) {
        String authNum = EmailVerificationService.makeAuthNumber();

        String content = makeMessageContent(authNum);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setFrom(senderAddress);
        message.setSubject("[Double Shop] 인증번호 안내 메일");
        message.setText(content);
        mailSender.send(message);

        session.setAttribute(email, authNum);
        session.setMaxInactiveInterval(LIMIT_TIME);
    }

    @Override
    public boolean verifyEmail(EmailVerificationRequestDto requestDto, HttpSession session) {
        String email = requestDto.getEmail();
        String verificationNum = requestDto.getVerificationNum();

        boolean result = session.getAttribute(email).equals(verificationNum);

        session.removeAttribute(requestDto.getEmail());

        return result;
    }

    @Override
    public String makeMessageContent(String verificationNumber) {
        MessageContentTemplate content = new MessageContentTemplate();

        content.setVerificationNum(verificationNumber);

        return content.parse();
    }

}
