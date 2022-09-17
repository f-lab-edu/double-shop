package com.project.doubleshop.domain.member.infrastructure.token;

import static org.springframework.util.ClassUtils.*;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.project.doubleshop.domain.exception.NotFoundException;
import com.project.doubleshop.web.member.dto.AuthenticationRequest;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SimpleAuthenticationProvider implements AuthenticationProvider {

	private final TokenMemberAuthProcessor tokenMemberAuthProcessor;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		SimpleAuthenticationToken authenticationToken = (SimpleAuthenticationToken) authentication;
		return processMemberAuthentication(authenticationToken.authenticationRequest());
	}

	private Authentication processMemberAuthentication(AuthenticationRequest request) {
		try {
			return tokenMemberAuthProcessor.login(request.getPrincipal(), request.getCredential());
		} catch (NotFoundException e) {
			throw new UsernameNotFoundException(e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new BadCredentialsException(e.getMessage());
		} catch (DataAccessException e) {
			throw new AuthenticationServiceException(e.getMessage(), e);
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return isAssignable(SimpleAuthenticationToken.class, authentication);
	}
}