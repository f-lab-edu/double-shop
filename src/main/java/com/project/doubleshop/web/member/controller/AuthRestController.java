package com.project.doubleshop.web.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doubleshop.domain.exception.UnauthenticatedMemberException;
import com.project.doubleshop.domain.member.MemberFacade;
import com.project.doubleshop.domain.member.infrastructure.token.SimpleAuthentication;
import com.project.doubleshop.domain.member.infrastructure.token.TokenRoleManager;
import com.project.doubleshop.web.member.dto.AuthenticationResultDto;
import com.project.doubleshop.web.member.dto.AuthenticationRequest;
import com.project.doubleshop.web.member.dto.RequestRole;
import com.project.doubleshop.web.member.dto.ResultRole;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class AuthRestController {

	private final MemberFacade memberFacade;

	private final AuthenticationManager authenticationManager;

	private final TokenRoleManager tokenRoleManager;

	@PostMapping("auth")
	public ResponseEntity<AuthenticationResultDto> authentication(@RequestBody AuthenticationRequest authRequest) throws
		UnauthenticatedMemberException {
		return ResponseEntity.ok(memberFacade.login(authRequest.getPrincipal(), authRequest.getCredential()));

	}

	@PatchMapping("auth/admin")
	public ResponseEntity<ResultRole> addAdmin(@AuthenticationPrincipal SimpleAuthentication authentication,
		HttpServletRequest request, @RequestBody RequestRole requestRole) {
		return ResponseEntity.ok(tokenRoleManager.manage(request, authentication.getId(), requestRole));
	}
}
