package com.project.doubleshop.web.security;

import static java.util.Objects.*;
import static java.util.stream.Collectors.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import com.project.doubleshop.web.security.redis.SessionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SimpleAuthenticationTokenFilter extends GenericFilterBean {

	private SessionService sessionService;

	private final String headerKey;

	private final int expirySeconds;

	@Autowired
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws
		IOException,
		ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			String tokenKey = request.getHeader(headerKey);
			if (tokenKey != null) {
				try {
					// verify token
					SimpleToken currentToken = sessionService.findBySessionId(tokenKey);
					log.debug("authentication parse from: {}", currentToken);

					// if not expired
					if (!isExpired(currentToken)) {
						// refresh expired(if remain 10 min below)
						if (canRefresh(currentToken, 600)) {
							currentToken.resetExpiry(expirySeconds);
							sessionService.resetExpiry(tokenKey, currentToken);
						}

						Long id = currentToken.getId();
						String userId = currentToken.getUserId();
						String name = currentToken.getName();
						String email = currentToken.getEmail();

						List<GrantedAuthority> authorities = obtainAuthorities(currentToken);

						if (nonNull(id) && nonNull(userId) && nonNull(name) && nonNull(email) && authorities.size() > 0) {
							SimpleAuthenticationToken authentication =
								new SimpleAuthenticationToken(new SimpleAuthentication(id, userId, name, email), null, authorities);
							authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
							SecurityContextHolder.getContext().setAuthentication(authentication);
						}
					}
				} catch (Exception e) {
					log.warn("Token auth processing failed: {}", e.getMessage());
				}
			}
		} else {
			SecurityContextHolder.getContext().getAuthentication();
		}

		chain.doFilter(request, response);
	}

	private List<GrantedAuthority> obtainAuthorities(SimpleToken currentToken) {
		String[] roles = currentToken.getRoles();
		return roles == null || roles.length == 0 ?
			Collections.emptyList() :
			Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(toList());
	}

	private boolean isExpired(SimpleToken token) {
		long remain  = token.getExpiredAt().getTime() - System.currentTimeMillis();
		return remain > 0;
	}

	private boolean canRefresh(SimpleToken token, int seconds) {
		long remain = token.getExpiredAt().getTime() - System.currentTimeMillis();
		return remain < seconds * 1000L;
	}
}