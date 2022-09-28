package com.project.doubleshop.member.presentation.response;

import java.time.LocalDateTime;

import com.project.doubleshop.member.domain.Member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MemberJoinResponse {

	private String userId;

	private String name;

	private String email;

	private String phone;

	private LocalDateTime createTime;

	public MemberJoinResponse(Member member) {
		this.userId = member.getUserId();
		this.name = member.getName();
		this.email = member.getEmail();
		this.phone = member.getPhone();
		this.createTime = member.getCreateTime();
	}
}
