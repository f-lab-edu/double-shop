package com.project.doubleshop.web.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doubleshop.member.application.MemberService;
import com.project.doubleshop.member.infrastructure.token.TokenService;
import com.project.doubleshop.web.member.dto.MemberResult;
import com.project.doubleshop.web.member.dto.MemberInfoRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class MemberRestController {

	private final MemberService authMemberService;

	private final TokenService tokenService;

	@DeleteMapping("member/{id}/log-out")
	public ResponseEntity<Boolean> logOut(HttpServletRequest request) {
		String tokenKey = request.getHeader("x-auth-token");
		return ResponseEntity.ok(tokenService.invalidSession(tokenKey));
	}

	@PatchMapping("member/{id}/profile")
	public ResponseEntity<MemberResult> updateMyProfile(@PathVariable Long id, @RequestBody MemberInfoRequest requestBody) {
		return ResponseEntity.ok(
			new MemberResult(authMemberService.updateProfile(id, requestBody.getUserId(), requestBody.getName(),
				requestBody.getEmail(), requestBody.getPhone()))
		);
	}

	@PatchMapping("member/{id}/password")
	public ResponseEntity<Boolean> changePassword(@PathVariable Long id, @RequestBody Map<String, String> requestMap) {
		return ResponseEntity.ok(authMemberService.changePassword(id, requestMap));
	}

	@PostMapping("member/exists/user-id")
	public ResponseEntity<Boolean> checkDuplicateUserId(@RequestBody Map<String, String> requestMap) {
		return ResponseEntity.ok(authMemberService.isExists(requestMap));
	}

	@PostMapping("member/exists/email")
	public ResponseEntity<Boolean> checkDuplicateEmail(@RequestBody Map<String, String> requestMap) {
		return ResponseEntity.ok(authMemberService.isExists(requestMap));
	}
}
