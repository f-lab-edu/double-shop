package com.project.doubleshop.web.security;

import com.project.doubleshop.domain.member.entity.Member;

import lombok.Getter;

@Getter
public class AuthenticationResult {

	private final String sessionToken;

	private final Member member;

	public AuthenticationResult(String sessionToken, Member member) {
		this.sessionToken = sessionToken;
		this.member = member;
	}

	@Override
	public String toString() {
		return "AuthenticationResult{" +
			"sessionToken='" + sessionToken + '\'' +
			", member=" + member +
			'}';
	}
}
