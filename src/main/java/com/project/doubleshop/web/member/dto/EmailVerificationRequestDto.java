package com.project.doubleshop.web.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailVerificationRequestDto {

    private String email;

    private String verificationNum;

}
