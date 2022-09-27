package com.project.doubleshop.member.infrastructure;

import com.project.doubleshop.exception.NotFoundException;
import com.project.doubleshop.member.domain.Member;
import com.project.doubleshop.member.domain.MemberRepository;
import com.project.doubleshop.member.infrastructure.token.SimpleAuthentication;
import com.project.doubleshop.web.member.dto.MemberResult;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberInfoFinder {
	private final MemberRepository memberRepository;

	public MemberResult find(SimpleAuthentication authentication) {
		Long memberId = authentication.getId();
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new NotFoundException(String.format("Id [%d] NotFound", memberId)));
		return new MemberResult(member);
	}
}
