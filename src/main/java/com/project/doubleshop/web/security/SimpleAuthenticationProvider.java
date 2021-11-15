package com.project.doubleshop.web.security;

import static com.project.doubleshop.web.security.SimpleAuthenticationTokenFilter.*;
import static org.springframework.security.core.authority.AuthorityUtils.*;
import static org.springframework.util.ClassUtils.*;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.doubleshop.domain.exception.MemberNotFoundException;
import com.project.doubleshop.domain.member.entity.v2.Member;
import com.project.doubleshop.domain.member.service.AuthMemberService;
import com.project.doubleshop.domain.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimpleAuthenticationProvider implements AuthenticationProvider {

	private final AuthMemberService memberService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		SimpleAuthenticationToken authenticationToken = (SimpleAuthenticationToken) authentication;
		return processMemberAuthentication(authenticationToken.authenticationRequest());
	}

	private Authentication processMemberAuthentication(AuthenticationRequest request) {
		try {
			Member member = memberService.login(request.getPrincipal(), request.getCredential());
			SimpleAuthenticationToken authenticated =
				new SimpleAuthenticationToken(member.getId(), null, createAuthorityList(Role.USER.value()));

			// 세션 서버 선택이 확정되면, 코드 변경.
			String tokenKey = UUID.randomUUID().toString();
			SimpleToken tokenValue = new SimpleToken(member.getId(), member.getName(), member.getEmail(),
				new String[] {Role.USER.value()});

			sessionMap.put(tokenKey, tokenValue);
			authenticated.setDetails(new AuthenticationResult(tokenKey, tokenValue));
			return authenticated;
		} catch (MemberNotFoundException e) {
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
