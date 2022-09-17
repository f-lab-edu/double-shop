package com.project.doubleshop.domain.member.presentation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doubleshop.domain.exception.UnauthenticatedMemberException;
import com.project.doubleshop.domain.member.application.MemberFacade;
import com.project.doubleshop.domain.member.infrastructure.AuthenticationResult;
import com.project.doubleshop.domain.member.infrastructure.token.SimpleAuthentication;
import com.project.doubleshop.domain.member.infrastructure.token.TokenRoleManager;
import com.project.doubleshop.domain.member.presentation.request.AuthenticationRequest;
import com.project.doubleshop.web.member.dto.RequestRole;
import com.project.doubleshop.web.member.dto.ResultRole;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class AuthRestController {

	private final MemberFacade memberFacade;

	private final TokenRoleManager tokenRoleManager;

	@PostMapping("auth")
	public ResponseEntity<AuthenticationResult> authentication(@RequestBody AuthenticationRequest authRequest) throws
		UnauthenticatedMemberException {
		return ResponseEntity.ok(memberFacade.login(authRequest.getPrincipal(), authRequest.getCredential()));

	}

	@PatchMapping("auth/admin")
	public ResponseEntity<ResultRole> addAdmin(@AuthenticationPrincipal SimpleAuthentication authentication,
		HttpServletRequest request, @RequestBody RequestRole requestRole) {
		return ResponseEntity.ok(tokenRoleManager.manage(request, authentication.getId(), requestRole));
	}
}
