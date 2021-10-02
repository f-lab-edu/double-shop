package com.project.doubleshop.domain.utils.verification.email;

public class EmailContentTemplate {

    private String verificationNum;

    public void setVerificationNum(String verificationNum) {
        this.verificationNum = verificationNum;
    }

    public String parse() {
        return String.format("[Double Shop] 인증 번호는 %s입니다.", verificationNum);
    }

}
