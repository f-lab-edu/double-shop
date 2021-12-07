package com.project.doubleshop.web.member.dto;

import com.project.doubleshop.web.config.security.SimpleToken;

import lombok.Getter;

@Getter
public class AuthenticationResultDto {

	private final String sessionToken;

	private final SimpleToken member;

	public AuthenticationResultDto(AuthenticationResult source) {
		this.sessionToken = source.getSessionToken();
		this.member = source.getSimpleToken();
	}
}
