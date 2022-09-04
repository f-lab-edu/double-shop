package com.project.doubleshop.web.member.dto;

import com.project.doubleshop.domain.utils.annotation.Password;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class JoinRequest {

	private String userId;

	@Password(message = "Password must contains at least one uppercase letter, "
		+ "one lowercase letter, one number and one special character between 10 ~ 50 characters...")
	private String credential;

	private String name;

	private String email;

	private String phone;
}
