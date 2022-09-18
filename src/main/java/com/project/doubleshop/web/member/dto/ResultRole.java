package com.project.doubleshop.web.member.dto;

import com.project.doubleshop.member.infrastructure.token.Role;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
@Getter
public class ResultRole {
	private String userId;
	private Role role;
}
