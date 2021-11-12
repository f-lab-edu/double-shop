package com.project.doubleshop.web.security;

import com.project.doubleshop.domain.member.entity.Member;

import lombok.Getter;

@Getter
public class AuthenticationResult {

	private final String sessionToken;

	private final SimpleToken simpleToken;

	public AuthenticationResult(String sessionToken, SimpleToken simpleToken) {
		this.sessionToken = sessionToken;
		this.simpleToken = simpleToken;
	}

	@Override
	public String toString() {
		return "AuthenticationResult{" +
			"sessionToken='" + sessionToken + '\'' +
			", simpleToken=" + simpleToken +
			'}';
	}
}
