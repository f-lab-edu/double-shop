package com.project.doubleshop.web.member.dto;

import com.project.doubleshop.web.config.security.Role;

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
