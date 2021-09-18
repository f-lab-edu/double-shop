package com.project.doubleshop.service;

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
        int authNum = randomRange(100000, 999999); // 인증 번호 생성 (100000 ~ 999999)

        String targetNum = "+" + country + phoneNum; // 수신자 휴대폰 번호: +(국가 번호) (전화 번호)

        String authMessage = "[Double Shop] 인증 번호 " + authNum + "를 입력하세요.";

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

        return authNum;
    }

    private static int randomRange(int n1, int n2) {
        return (int) ((Math.random() * (n2 - n1 + 1)) + n1);
    }

    // 사용자가 입력한 인증 번호와 세션에 저장된 인증 번호 비교
    public boolean verifySms(String phoneNum, String inputNum, HttpSession session) {
        return session.getAttribute(phoneNum).equals(inputNum);
    }

}
