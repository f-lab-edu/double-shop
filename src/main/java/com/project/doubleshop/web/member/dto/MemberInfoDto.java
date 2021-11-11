package com.project.doubleshop.web.member.dto;

import com.project.doubleshop.domain.member.entity.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberInfoDto {

    private String userId;

    private String name;

    private String phone;

    private String email;

}
