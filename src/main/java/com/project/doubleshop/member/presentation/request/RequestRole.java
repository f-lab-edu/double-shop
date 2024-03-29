package com.project.doubleshop.member.presentation.request;

import com.project.doubleshop.member.infrastructure.token.Role;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
@Getter
public class RequestRole {
	private Role role;
	private String adminKey;
}
