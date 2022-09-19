package com.project.doubleshop.member.presentation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doubleshop.common.ApiResult;
import com.project.doubleshop.exception.UnauthenticatedMemberException;
import com.project.doubleshop.member.application.MemberFacade;
import com.project.doubleshop.member.infrastructure.AuthenticationResult;
import com.project.doubleshop.member.infrastructure.token.SimpleAuthentication;
import com.project.doubleshop.member.infrastructure.token.TokenRoleManager;
import com.project.doubleshop.member.presentation.request.AuthenticationRequest;
import com.project.doubleshop.web.member.dto.RequestRole;
import com.project.doubleshop.web.member.dto.ResultRole;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class MemberAuthApi implements MemberAuthSpecification {

	private final MemberFacade memberFacade;

	private final TokenRoleManager tokenRoleManager;

	@PostMapping("v2/auth")
	public ApiResult<AuthenticationResult> authentication(@RequestBody AuthenticationRequest authRequest) throws
		UnauthenticatedMemberException {
		AuthenticationResult result = memberFacade.login(authRequest.getPrincipal(), authRequest.getCredential());
		return ApiResult.OK(result);

	}

	@PatchMapping("auth/admin")
	public ApiResult<ResultRole> addAdmin(@AuthenticationPrincipal SimpleAuthentication authentication,
		HttpServletRequest request, @RequestBody RequestRole requestRole) {
		ResultRole result = tokenRoleManager.manage(request, authentication.getId(), requestRole);
		return ApiResult.OK(result);
	}
}
