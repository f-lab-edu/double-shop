package com.project.doubleshop.web.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doubleshop.domain.exception.UnauthenticatedMemberException;
import com.project.doubleshop.web.member.dto.AuthenticationResultDto;
import com.project.doubleshop.web.member.dto.AuthenticationRequest;
import com.project.doubleshop.web.member.dto.AuthenticationResult;
import com.project.doubleshop.web.config.security.SimpleAuthenticationToken;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class AuthRestController {

	private final AuthenticationManager authenticationManager;

	@PostMapping("auth")
	public ResponseEntity<AuthenticationResultDto> authentication(@RequestBody AuthenticationRequest authRequest) throws
		UnauthenticatedMemberException {
		try {
			SimpleAuthenticationToken authToken = new SimpleAuthenticationToken(
				authRequest.getPrincipal(), authRequest.getCredential());
			Authentication authentication = authenticationManager.authenticate(authToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			return ResponseEntity.ok(new AuthenticationResultDto((AuthenticationResult) authentication.getDetails()));
		} catch (AuthenticationException e) {
			throw new UnauthenticatedMemberException(e.getMessage(), HttpStatus.UNAUTHORIZED.value());
		}
	}
}
