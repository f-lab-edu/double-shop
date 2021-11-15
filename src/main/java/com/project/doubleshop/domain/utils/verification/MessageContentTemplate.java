package com.project.doubleshop.domain.utils.verification;

import lombok.Setter;

@Setter
public class MessageContentTemplate {

    private String verificationNum;

    public String parse() {
        return String.format("[Double Shop] 인증 번호는 %s입니다.", verificationNum);
    }

}
