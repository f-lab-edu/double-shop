package com.project.doubleshop.web.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
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