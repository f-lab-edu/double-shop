package com.project.doubleshop.web.member.dto;

import com.project.doubleshop.web.config.security.SimpleToken;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationResultDto {

	private String sessionToken;

	private SimpleToken member;

	public AuthenticationResultDto(AuthenticationResult source) {
		this.sessionToken = source.getSessionToken();
		this.member = source.getSimpleToken();
	}
}
