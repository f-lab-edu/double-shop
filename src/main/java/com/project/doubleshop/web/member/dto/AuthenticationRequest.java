package com.project.doubleshop.web.member.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AuthenticationRequest {

	private String principal;

	private String credential;

	@Override
	public String toString() {
		return "AuthenticationRequest{" +
			"principal='" + principal + '\'' +
			", credential='" + credential + '\'' +
			'}';
	}
}
