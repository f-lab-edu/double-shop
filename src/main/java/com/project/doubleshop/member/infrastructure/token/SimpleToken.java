package com.project.doubleshop.member.infrastructure.token;

import java.util.ArrayList;
import java.util.Arrays;
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

	private Long issuedAt;

	private Long expiredAt;

	private String clientIp;

	private String[] roles;

	public void resetExpiry(int expirySeconds) {
		this.expiredAt = System.currentTimeMillis() + expirySeconds * 1_000_000_000L;
	}

	public SimpleToken(Long id, String userId, String name, String email, Long issuedAt, Long expiredAt,
		String[] roles) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.issuedAt = issuedAt;
		this.expiredAt = expiredAt;
		this.roles = roles;
	}

	public void addRole(Role role) {
		List<String> roles = new ArrayList<>(Arrays.asList(this.roles));
		if (!roles.contains(role.value())) {
			roles.add(role.value());
			this.roles = roles.toArray(new String[0]);
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
			", clientIp='" + clientIp + '\'' +
			", roles=" + Arrays.toString(roles) +
			'}';
	}
}
