package com.project.doubleshop.web.member.dto;

import com.project.doubleshop.web.config.security.SimpleToken;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationResult {

	private String sessionToken;

	private SimpleToken simpleToken;

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
