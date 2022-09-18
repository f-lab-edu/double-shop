package com.project.doubleshop.member.infrastructure;

import com.project.doubleshop.exception.DuplicateMemberException;
import com.project.doubleshop.member.application.JoinRequest;
import com.project.doubleshop.member.domain.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRegisterVerifier {
	private final MemberRepository memberRepository;

	public void verify(JoinRequest requestBody) {
		String userId = requestBody.getUserId();
		String email = requestBody.getEmail();

		if (isDuplicated(userId, email))
			throw new DuplicateMemberException(String.format("UserId [%s], email [%s] already exisits..", userId, email));
	}

	private boolean isDuplicated(String userId, String email) {
		return memberRepository.findByUserId(userId).isPresent() || memberRepository.findByEmail(email).isPresent();
	}
}
