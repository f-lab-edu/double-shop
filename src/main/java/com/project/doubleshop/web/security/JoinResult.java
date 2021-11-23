package com.project.doubleshop.web.security;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.member.entity.v2.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JoinResult {

	private String userId;

	private String name;

	private String email;

	private String phone;

	private LocalDateTime createTime;

	public JoinResult(Member member) {
		this.userId = member.getUserId();
		this.name = member.getName();
		this.email = member.getEmail();
		this.phone = member.getPhone();
		this.createTime = member.getCreateTime();
	}
}
