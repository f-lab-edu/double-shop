package com.project.doubleshop.web.security;

import java.util.Arrays;
import java.util.Date;

import lombok.Getter;

@Getter
public class SimpleToken {

	private Long userId;

	private String name;

	private String email;

	private Date issuedAt;

	private Date expiredAt;

	private String[] roles;

	public SimpleToken(Long userId, String name, String email, String[] roles) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.roles = roles;
	}

	public SimpleToken(Long userId, String name, String email, Date issuedAt, Date expiredAt, String[] roles) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.issuedAt = issuedAt;
		this.expiredAt = expiredAt;
		this.roles = roles;
	}

	public void resetExpiry(int expirySeconds) {
		Date now = new Date();
		this.expiredAt = new Date(now.getTime() + expirySeconds * 1000L);
	}

	@Override
	public String toString() {
		return "SimpleToken{" +
			"userId=" + userId +
			", issuedAt=" + issuedAt +
			", expiredAt=" + expiredAt +
			", roles=" + Arrays.toString(roles) +
			'}';
	}
}
