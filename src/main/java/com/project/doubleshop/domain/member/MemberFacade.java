package com.project.doubleshop.domain.member;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberFacade {
	private final MemberAuthProcessor memberAuthProcessor;

	public void login(String userId, String password) {
		memberAuthProcessor.login(userId, password);
	}
}
