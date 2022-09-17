package com.project.doubleshop.domain.member;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.doubleshop.domain.exception.UnauthenticatedMemberException;
import com.project.doubleshop.domain.member.infrastructure.token.SimpleAuthenticationToken;
import com.project.doubleshop.web.member.dto.AuthenticationResult;
import com.project.doubleshop.web.member.dto.AuthenticationResultDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberFacade {
	private final AuthenticationManager authenticationManager;

	public AuthenticationResultDto login(String userId, String password) {
		try {
			SimpleAuthenticationToken authToken = new SimpleAuthenticationToken(
				userId, password);
			Authentication authentication = authenticationManager.authenticate(authToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			return new AuthenticationResultDto((AuthenticationResult) authentication.getDetails());
		} catch (AuthenticationException e) {
			throw new UnauthenticatedMemberException(e.getMessage(), HttpStatus.UNAUTHORIZED.value());
		}
	}
}
