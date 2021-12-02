package com.project.doubleshop.web.member.dto;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateMemberRequest {

	private Long id;

	private String userId;

	private String name;

	private String email;

	private String phone;

}
