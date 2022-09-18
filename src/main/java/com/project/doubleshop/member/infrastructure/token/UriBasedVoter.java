package com.project.doubleshop.member.infrastructure.token;

import static org.springframework.util.ClassUtils.*;

import java.util.Collection;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.RequestMatcher;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UriBasedVoter implements AccessDecisionVoter<FilterInvocation> {

	private final RequestMatcher requiresAuthorizationRequestMatcher;
	private final Function<String, Long> idExtractor;

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return isAssignable(FilterInvocation.class, clazz);
	}

	@Override
	public int vote(Authentication authentication, FilterInvocation fi, Collection<ConfigAttribute> attributes) {
		HttpServletRequest request = fi.getRequest();

		if (!requiresAuthorizationRequestMatcher.matches(request)) {
			return ACCESS_GRANTED;
		}

		if (!isAssignable(SimpleAuthenticationToken.class, authentication.getClass())) {
			return ACCESS_ABSTAIN;
		}

		SimpleAuthentication principal = (SimpleAuthentication)authentication.getPrincipal();
		Long targetId = idExtractor.apply(request.getRequestURI());

		// 본인 확인
		if (principal.getId().equals(targetId)) {
			return ACCESS_GRANTED;
		}

		return ACCESS_DENIED;
	}
}
