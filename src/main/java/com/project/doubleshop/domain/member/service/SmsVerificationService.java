package com.project.doubleshop.domain.member.service;

import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import javax.servlet.http.HttpSession;

/**
 * Twilio API를 활용한 SMS
 * 회원가입 시 사용자가 입력한 휴대폰 번호로 인증 번호 전송
 */
public class SmsVerificationService {

    // 발급받은 시드와 토큰 값
    public static final String ACCOUNT_SID = "AC86c9a7f3df4e9b910b4fa64ff13db261";
    public static final String AUTH_TOKEN = "38b21c4bc13682910791a82d148dc4b5";
    public static final String SENDER_NUM = "+14405167177";

    // 인증 번호를 전송하고, 발송 데이터를 세션에 저장
    public int sendSms(String country, String phoneNum, HttpSession session) {

        // 인증 번호 생성 (100000 ~ 999999)
        int authNum = randomRange(100000, 999999);

        // 수신자 휴대폰 번호: +(국가 번호) (전화 번호)
        String targetNum = String.format("+%s%s", country, phoneNum);

        String authMessage = String.format("[Double Shop] 인증 번호 %d를 입력하세요.", authNum);

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

        return authNum;
    }

    // 사용자가 입력한 인증 번호와 세션에 저장된 인증 번호 비교
    public boolean verifySms(String phoneNum, String inputNum, HttpSession session) {
        boolean result = session.getAttribute(phoneNum).equals(inputNum);

        session.removeAttribute("phoneNum");

        return result;
    }

    private static int randomRange(int n1, int n2) {
        return (int) ((Math.random() * (n2 - n1 + 1)) + n1);
    }

}
