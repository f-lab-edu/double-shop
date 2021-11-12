package com.project.doubleshop.web.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class SimpleAuthenticationToken extends AbstractAuthenticationToken {

	private final Object principal;

	private String credentials;

	public SimpleAuthenticationToken(Object principal, String credential) {
		super(null);
		super.setAuthenticated(false);

		this.principal = principal;
		this.credentials = credential;
	}

	public SimpleAuthenticationToken(
		Collection<? extends GrantedAuthority> authorities, Object principal, String credential) {
		super(authorities);
		super.setAuthenticated(true);

		this.principal = principal;
		this.credentials = credential;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}

	@Override
	public String getCredentials() {
		return credentials;
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		credentials = null;
	}

	@Override
	public String toString() {
		return "AuthenticationToken{" +
			"principal=" + principal +
			", credentials='" + "[PROTECTED]" + '\'' +
			'}';
	}
}
