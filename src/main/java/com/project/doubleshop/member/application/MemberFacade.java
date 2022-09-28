package com.project.doubleshop.member.application;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.exception.UnauthenticatedMemberException;
import com.project.doubleshop.member.domain.Member;
import com.project.doubleshop.member.infrastructure.MemberInfoManager;
import com.project.doubleshop.member.infrastructure.MemberRegisterManager;
import com.project.doubleshop.member.infrastructure.token.SimpleAuthentication;
import com.project.doubleshop.member.infrastructure.token.SimpleAuthenticationToken;
import com.project.doubleshop.member.infrastructure.AuthenticationResult;
import com.project.doubleshop.member.infrastructure.token.TokenService;
import com.project.doubleshop.member.presentation.request.MemberInfoRequest;
import com.project.doubleshop.member.presentation.response.MemberInfoResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberFacade {
	private final AuthenticationManager authenticationManager;
	private final MemberRegisterManager memberRegister;
	private final MemberInfoManager memberInfoManager;
	private final TokenService tokenService;

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
	public MemberInfoResponse find(SimpleAuthentication authentication) {
		return memberInfoManager.find(authentication);
	}

	@Transactional
	public MemberInfoResponse update(Long id, MemberInfoRequest requestBody) {
		return memberInfoManager.update(id, requestBody);
	}

	@Transactional
	public Boolean changePasswd(Long id, Map<String, String> requestMap) {
		return memberInfoManager.changePasswd(id, requestMap);
	}

	@Transactional(readOnly = true)
	public Boolean isDuplicated(Map<String, String> requestMap) {
		return memberInfoManager.isDuplicated(requestMap);
	}

	public Boolean invalidSession(String tokenKey) {
		return tokenService.invalidSession(tokenKey);
	}
}
