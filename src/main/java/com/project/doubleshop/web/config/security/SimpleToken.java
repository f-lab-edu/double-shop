package com.project.doubleshop.web.config.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

	private String clientIp;

	private String[] roles;

	public void resetExpiry(int expirySeconds) {
		Date now = new Date();
		this.expiredAt = new Date(now.getTime() + expirySeconds * 1000L);
	}

	public void addRole(Role role) {
		List<String> roles = new ArrayList<>(Arrays.asList(this.roles));
		if (!roles.contains(role.value())) {
			roles.add(role.value());
			this.roles = roles.toArray(new String[roles.size()]);
		} else {
			throw new IllegalArgumentException("Invalid role requested.");
		}
		this.roles = new String[] {Role.USER.value(), Role.ADMIN.value()};
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
