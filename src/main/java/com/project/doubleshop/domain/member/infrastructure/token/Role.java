package com.project.doubleshop.domain.member.infrastructure.token;

public enum Role {

	USER("ROLE_USER"),
	USER_VENDOR("ROLE_VENDOR"),
	ADMIN("ROLE_ADMIN");

	private final String value;

	Role(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

	public static Role of(String name) {
		for (Role role : Role.values()) {
			if (role.name().equalsIgnoreCase(name)) {
				return role;
			}
		}
		return null;
	}

}
