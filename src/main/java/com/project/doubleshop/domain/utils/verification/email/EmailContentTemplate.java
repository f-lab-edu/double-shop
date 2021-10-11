package com.project.doubleshop.domain.utils.verification.email;

import lombok.Setter;

@Setter
public class EmailContentTemplate {

    private String verificationNum;

    public String parse() {
        return String.format("[Double Shop] 인증 번호는 %s입니다.", verificationNum);
    }

}
