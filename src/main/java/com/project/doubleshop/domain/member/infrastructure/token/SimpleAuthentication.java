package com.project.doubleshop.domain.member.infrastructure.token;

import lombok.Getter;

@Getter
public class SimpleAuthentication {

	private final Long id;

	private final String userId;

	private final String name;

	private final String email;

	public SimpleAuthentication(Long id, String userId, String name, String email) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.email = email;
	}
}
