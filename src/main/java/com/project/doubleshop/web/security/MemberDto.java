package com.project.doubleshop.web.security;

import java.time.LocalDateTime;
import java.util.Date;

import com.project.doubleshop.domain.member.entity.v2.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberDto {

	private final Long id;

	private final String userId;

	private final String name;

	private final String email;

	private final String phone;

	private final Integer count;

	private final LocalDateTime lastLoginTime;

	public MemberDto(Member member) {
		this.id = member.getId();
		this.userId = member.getUserId();
		this.name = member.getName();
		this.email = member.getEmail();
		this.phone = member.getPhone();
		this.count = member.getCount();
		this.lastLoginTime = member.getLastLoginTime();
	}
}
