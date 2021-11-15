package com.project.doubleshop.web.security;

import lombok.Getter;

@Getter
public class SimpleAuthentication {

	private final Long memberId;

	private final String name;

	private final String email;

	public SimpleAuthentication(Long memberId, String name, String email) {
		this.memberId = memberId;
		this.name = name;
		this.email = email;
	}
}
