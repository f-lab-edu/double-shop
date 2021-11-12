package com.project.doubleshop.web.security;

import lombok.Getter;

@Getter
public class SimpleAuthentication {

	private final Long id;

	private final String name;

	private final String email;

	public SimpleAuthentication(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
}
