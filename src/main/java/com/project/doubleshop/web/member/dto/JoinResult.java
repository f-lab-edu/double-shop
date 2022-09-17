package com.project.doubleshop.web.member.dto;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.member.domain.Member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
