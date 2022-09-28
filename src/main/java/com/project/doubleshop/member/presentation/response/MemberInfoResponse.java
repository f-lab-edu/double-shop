package com.project.doubleshop.member.presentation.response;

import java.time.LocalDateTime;

import com.project.doubleshop.member.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberInfoResponse {

	private final Long id;

	private final String userId;

	private final String name;

	private final String email;

	private final String phone;

	private final Integer count;

	private final LocalDateTime lastLoginTime;

	public MemberInfoResponse(Member member) {
		this.id = member.getId();
		this.userId = member.getUserId();
		this.name = member.getName();
		this.email = member.getEmail();
		this.phone = member.getPhone();
		this.count = member.getCount();
		this.lastLoginTime = member.getLastLoginTime();
	}
}
