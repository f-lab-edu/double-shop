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

@Service
public class TwilioSmsVerificationService implements SmsVerificationService {

    @Value("${account-sid}")
    private String ACCOUNT_SID;

    @Value("${auth-token}")
    private String AUTH_TOKEN;

    @Value("${sender-num}")
    private String SENDER_NUM;

    @Override
    public void sendMessage(String phoneNum, HttpSession session) {

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
