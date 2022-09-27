package com.project.doubleshop.member.infrastructure;

import com.project.doubleshop.member.domain.Member;
import com.project.doubleshop.member.infrastructure.token.SimpleAuthentication;
import com.project.doubleshop.web.member.dto.MemberResult;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberInfoFinder {
	private final MemberCrudService memberCrudService;

	public MemberResult find(SimpleAuthentication authentication) {
		Long memberId = authentication.getId();
		Member member = memberCrudService.findById(memberId);
		return new MemberResult(member);
	}
}
