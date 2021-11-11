package com.project.doubleshop.web.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberFindResponseDto {

    private String email;

    private String phone;

}
