package com.project.doubleshop.web.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberFindRequestDto {

    private String email;

    private String phone;

}
