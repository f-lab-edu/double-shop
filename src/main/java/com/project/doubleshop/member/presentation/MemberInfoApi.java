package com.project.doubleshop.member.presentation;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doubleshop.common.ApiResult;
import com.project.doubleshop.member.application.MemberFacade;
import com.project.doubleshop.member.infrastructure.token.SimpleAuthentication;
import com.project.doubleshop.web.member.dto.MemberResult;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2")
public class MemberInfoApi {
	private final MemberFacade memberFacade;

	@GetMapping(value = "member/me", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResult<MemberResult> me(@AuthenticationPrincipal SimpleAuthentication authentication) {
		return ApiResult.OK(memberFacade.find(authentication));
	}
}
