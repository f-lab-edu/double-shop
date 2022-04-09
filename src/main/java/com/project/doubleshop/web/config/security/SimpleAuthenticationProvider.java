package com.project.doubleshop.web.config.security;

import static org.springframework.security.core.authority.AuthorityUtils.*;
import static org.springframework.util.ClassUtils.*;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.doubleshop.domain.exception.NotFoundException;
import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.domain.member.service.MemberService;
import com.project.doubleshop.domain.member.service.legacy.AuthMemberService;
import com.project.doubleshop.domain.member.service.TokenService;
import com.project.doubleshop.domain.utils.IPUtils;
import com.project.doubleshop.web.member.dto.AuthenticationRequest;
import com.project.doubleshop.web.member.dto.AuthenticationResult;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimpleAuthenticationProvider implements AuthenticationProvider {

	private MemberService authMemberService;

	private SimpleTokenConfigurer simpleTokenConfigurer;

	private TokenService tokenService;

	private HttpServletRequest httpServletRequest;

	@Autowired
	public void setAuthMemberService(MemberService authMemberService) {
		this.authMemberService = authMemberService;
	}

	@Autowired
	public void setSimpleTokenConfigurer(SimpleTokenConfigurer simpleTokenConfigurer) {
		this.simpleTokenConfigurer = simpleTokenConfigurer;
	}

	@Autowired
	public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}

	@Autowired
	public void setTokenService(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		SimpleAuthenticationToken authenticationToken = (SimpleAuthenticationToken) authentication;
		return processMemberAuthentication(authenticationToken.authenticationRequest());
	}

	private Authentication processMemberAuthentication(AuthenticationRequest request) {
		try {
			Member member = authMemberService.login(request.getPrincipal(), request.getCredential());
			SimpleAuthenticationToken authenticated =
				new SimpleAuthenticationToken(member.getId(), null, createAuthorityList(Role.USER.value()));

			long now = System.currentTimeMillis();
			int expirySeconds = simpleTokenConfigurer.getExpirySeconds();
			
			String tokenKey = UUID.randomUUID().toString();
			String authClientAddress = IPUtils.getClientIpAddress(httpServletRequest);
			SimpleToken tokenValue = new SimpleToken(member.getId(), member.getUserId(), member.getName(),
				member.getEmail(), now, now + expirySeconds * 1000L, authClientAddress, new String[] {Role.USER.value()});

			tokenService.saveSession(tokenKey, tokenValue);
			authenticated.setDetails(new AuthenticationResult(tokenKey, tokenValue));
			return authenticated;
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
