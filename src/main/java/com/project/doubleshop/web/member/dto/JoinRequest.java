package com.project.doubleshop.web.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JoinRequest {

	private String userId;

	private String credential;

	private String name;

	private String email;

	private String phone;
}
