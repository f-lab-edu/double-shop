package com.project.doubleshop.member.infrastructure;

import com.project.doubleshop.member.domain.Member;
import com.project.doubleshop.web.member.dto.MemberInfoRequest;
import com.project.doubleshop.web.member.dto.MemberResult;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberInfoManager {
	MemberCrudService memberCrudService;

	public MemberResult update(Long id, MemberInfoRequest requestBody) {
		Member member = memberCrudService.findById(id);
		member.updateProfile(requestBody.getUserId(), requestBody.getName(), requestBody.getEmail(),
			requestBody.getPhone());
		return new MemberResult(member);
	}
}
