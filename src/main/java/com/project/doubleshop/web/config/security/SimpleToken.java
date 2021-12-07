package com.project.doubleshop.web.config.security;

import java.util.Arrays;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleToken {

	private Long id;

	private String userId;

	private String name;

	private String email;

	private Date issuedAt;

	private Date expiredAt;

	private String[] roles;

	public void resetExpiry(int expirySeconds) {
		Date now = new Date();
		this.expiredAt = new Date(now.getTime() + expirySeconds * 1000L);
	}

	@Override
	public String toString() {
		return "SimpleToken{" +
			"id=" + id +
			", userId='" + userId + '\'' +
			", name='" + name + '\'' +
			", email='" + email + '\'' +
			", issuedAt=" + issuedAt +
			", expiredAt=" + expiredAt +
			", roles=" + Arrays.toString(roles) +
			'}';
	}
}
