package com.project.doubleshop.domain.member.service;

import com.project.doubleshop.domain.exception.AuthenticationNumberMismatchException;
import com.project.doubleshop.domain.utils.verification.MessageContentTemplate;
import com.project.doubleshop.web.member.dto.SmsVerificationRequestDto;
import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Twilio API를 활용한 SMS
 * 회원가입 시 사용자가 입력한 휴대폰 번호로 인증 번호 전송
 */
@Service
public class TwilioSmsVerificationService implements SmsVerificationService {

    @Value("${account-sid}")
    private String ACCOUNT_SID;

    @Value("${auth-token}")
    private String AUTH_TOKEN;

    @Value("${sender-num}")
    private String SENDER_NUM;

    // 인증 번호를 전송하고, 발송 데이터를 세션에 저장
    @Override
    public void sendMessage(String phoneNum, HttpSession session) {

        // 인증 번호 생성 (100000 ~ 999999)
        String authNum = SmsVerificationService.makeAuthNumber();

        // 수신자 휴대폰 번호: +(국가 번호) (전화 번호)
        String targetNum = String.format("+82%s", phoneNum);

        String authMessage = makeMessageContent(authNum);

        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            Message message = Message.creator(
                    new PhoneNumber(targetNum),
                    new PhoneNumber(SENDER_NUM),
                    authMessage)
                    .create();
        } catch (TwilioException e) {
            throw new RuntimeException("TwilioException occurred.", e);
        }

        session.setAttribute(phoneNum, authNum);
        session.setMaxInactiveInterval(60 * 10); // 인증 번호의 유효 시간 설정 (10분)
    }

    // 사용자가 입력한 인증 번호와 세션에 저장된 인증 번호 비교
    @Override
    public void verifyMessage(SmsVerificationRequestDto requestDto, HttpSession session) {
        String phoneNum = requestDto.getPhoneNum();
        String inputNum = requestDto.getVerificationNum();

        boolean result = session.getAttribute(phoneNum).equals(inputNum);

        if (!result) {
            throw new AuthenticationNumberMismatchException("인증번호가 일치하지 않습니다.");
        }

        session.removeAttribute("phoneNum");
    }

    @Override
    public String makeMessageContent(String verificationNumber) {
        MessageContentTemplate content = new MessageContentTemplate();

        content.setVerificationNum(verificationNumber);

        return content.parse();
    }

}
