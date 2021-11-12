package com.project.doubleshop.web.security;

import lombok.Getter;

@Getter
public class SimpleAuthentication {

	private final Long userId;

	private final String name;

	private final String email;

	public SimpleAuthentication(Long userId, String name, String email) {
		this.userId = userId;
		this.name = name;
		this.email = email;
	}
}
