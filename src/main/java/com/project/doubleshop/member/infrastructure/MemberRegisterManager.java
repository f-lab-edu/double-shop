package com.project.doubleshop.member.infrastructure;

import com.project.doubleshop.member.domain.Member;
import com.project.doubleshop.member.application.JoinRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRegisterManager {
	private final MemberRegisterVerifier memberRegisterVerifier;

	private final MemberRegisterProcessor memberRegisterProcessor;


	public Member join(JoinRequest requestBody) {
		memberRegisterVerifier.verify(requestBody);
		memberRegisterProcessor.registerWith(requestBody);
		return memberRegisterProcessor.getResult();
	}
}
