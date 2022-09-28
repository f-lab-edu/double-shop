package com.project.doubleshop.member.presentation.response;

import com.project.doubleshop.member.infrastructure.token.Role;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
@Getter
public class ResponseRole {
	private String userId;
	private Role role;
}
