package com.project.doubleshop.member.application;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.exception.UnauthenticatedMemberException;
import com.project.doubleshop.member.domain.Member;
import com.project.doubleshop.member.infrastructure.MemberInfoFinder;
import com.project.doubleshop.member.infrastructure.MemberRegisterManager;
import com.project.doubleshop.member.infrastructure.token.SimpleAuthentication;
import com.project.doubleshop.member.infrastructure.token.SimpleAuthenticationToken;
import com.project.doubleshop.member.infrastructure.AuthenticationResult;
import com.project.doubleshop.web.member.dto.MemberResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberFacade {
	private final AuthenticationManager authenticationManager;
	private final MemberRegisterManager memberRegister;
	private final MemberInfoFinder memberInfoFinder;

	public AuthenticationResult login(String userId, String password) {
		try {
			SimpleAuthenticationToken authToken = new SimpleAuthenticationToken(
				userId, password);
			Authentication authentication = authenticationManager.authenticate(authToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			return (AuthenticationResult) authentication.getDetails();
		} catch (AuthenticationException e) {
			throw new UnauthenticatedMemberException(e.getMessage(), HttpStatus.UNAUTHORIZED.value());
		}
	}

	public Member join(JoinRequest joinRequest) {
		return memberRegister.join(joinRequest);
	}

	@Transactional(readOnly = true)
	public MemberResult find(SimpleAuthentication authentication) {
		return memberInfoFinder.find(authentication);
	}
}
