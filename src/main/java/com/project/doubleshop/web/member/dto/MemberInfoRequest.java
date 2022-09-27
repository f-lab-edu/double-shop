package com.project.doubleshop.web.member.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MemberInfoRequest {

	private String userId;

	private String name;

	private String email;

	private String phone;

}
