package com.project.doubleshop.web.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doubleshop.domain.exception.UnauthenticatedMemberException;
import com.project.doubleshop.web.config.security.SimpleAuthentication;
import com.project.doubleshop.web.config.security.TokenRoleManager;
import com.project.doubleshop.web.member.dto.AuthenticationResultDto;
import com.project.doubleshop.web.member.dto.AuthenticationRequest;
import com.project.doubleshop.web.member.dto.AuthenticationResult;
import com.project.doubleshop.web.config.security.SimpleAuthenticationToken;
import com.project.doubleshop.web.member.dto.RequestRole;
import com.project.doubleshop.web.member.dto.ResultRole;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class AuthRestController {

	private final AuthenticationManager authenticationManager;

	private final TokenRoleManager tokenRoleManager;

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

	@PatchMapping("auth/admin")
	public ResponseEntity<ResultRole> addAdmin(@AuthenticationPrincipal SimpleAuthentication authentication,
		HttpServletRequest request, @RequestBody RequestRole requestRole) {
		return ResponseEntity.ok(tokenRoleManager.manage(request, authentication.getId(), requestRole));
	}
}
