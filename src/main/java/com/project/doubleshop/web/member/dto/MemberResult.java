package com.project.doubleshop.web.member.dto;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.member.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResult {

	private final Long id;

	private final String userId;

	private final String name;

	private final String email;

	private final String phone;

	private final Integer count;

	private final LocalDateTime lastLoginTime;

	public MemberResult(Member member) {
		this.id = member.getId();
		this.userId = member.getUserId();
		this.name = member.getName();
		this.email = member.getEmail();
		this.phone = member.getPhone();
		this.count = member.getCount();
		this.lastLoginTime = member.getLastLoginTime();
	}
}
