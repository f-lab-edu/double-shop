package com.project.doubleshop.member.presentation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doubleshop.common.ApiResult;
import com.project.doubleshop.member.application.MemberFacade;
import com.project.doubleshop.member.infrastructure.token.SimpleAuthentication;
import com.project.doubleshop.member.presentation.response.MemberInfoResponse;
import com.project.doubleshop.member.presentation.request.MemberInfoRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2")
public class MemberInfoApi {
	private final MemberFacade memberFacade;

	@GetMapping(value = "member/me", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResult<MemberInfoResponse> me(@AuthenticationPrincipal SimpleAuthentication authentication) {
		return ApiResult.OK(memberFacade.find(authentication));
	}

	@PatchMapping(value = "member/{id}/profile")
	public ApiResult<MemberInfoResponse> update(@PathVariable Long id, @RequestBody MemberInfoRequest requestBody) {
		return ApiResult.OK(memberFacade.update(id, requestBody));
	}

	@PatchMapping("member/{id}/password")
	public ApiResult<Boolean> changePassword(@PathVariable Long id, @RequestBody Map<String, String> requestMap) {
		return ApiResult.OK(memberFacade.changePasswd(id, requestMap));
	}

	@PostMapping("member/exists/user-id")
	public ApiResult<Boolean> checkDuplicateUserId(@RequestBody Map<String, String> requestMap) {
		return ApiResult.OK(memberFacade.isDuplicated(requestMap));
	}

	@PostMapping("member/exists/email")
	public ApiResult<Boolean> checkDuplicateEmail(@RequestBody Map<String, String> requestMap) {
		return ApiResult.OK(memberFacade.isDuplicated(requestMap));
	}

	@DeleteMapping("member/{id}/log-out")
	public ApiResult<Boolean> logOut(HttpServletRequest request) {
		String tokenKey = request.getHeader("x-auth-token");
		return ApiResult.OK(memberFacade.invalidSession(tokenKey));
	}
}
