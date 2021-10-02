package com.project.doubleshop.web.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailVerificationRequestDto {

    private String email;

    private String VerificationNum;

}
