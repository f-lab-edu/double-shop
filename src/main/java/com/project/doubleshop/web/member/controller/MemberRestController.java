package com.project.doubleshop.web.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.domain.member.service.AuthMemberService;
import com.project.doubleshop.domain.member.service.SessionService;
import com.project.doubleshop.web.member.dto.JoinRequest;
import com.project.doubleshop.web.member.dto.JoinResult;
import com.project.doubleshop.web.member.dto.MemberDto;
import com.project.doubleshop.web.config.security.SimpleAuthentication;
import com.project.doubleshop.web.member.dto.UpdateMemberRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class MemberRestController {

	private final AuthMemberService authMemberService;

	private final SessionService sessionService;

	@GetMapping("member/me")
	public ResponseEntity<MemberDto> me(@AuthenticationPrincipal SimpleAuthentication authentication) {
		return ResponseEntity.ok(new MemberDto(authMemberService.findById(authentication.getId())));
	}

	@PostMapping("member/join")
	public ResponseEntity<JoinResult> join(@RequestBody JoinRequest requestBody) {
		Member member = authMemberService.join(requestBody.getUserId(), requestBody.getCredential(), requestBody.getName(),
			requestBody.getEmail(), requestBody.getPhone());
		return ResponseEntity.ok(new JoinResult(member));
	}

	@DeleteMapping("member/{id}/log-out")
	public ResponseEntity<Boolean> logOut(@PathVariable Long id, HttpServletRequest request) {
		String tokenHeader = request.getHeader("x-auth-token");
		return ResponseEntity.ok(sessionService.invalidSession(tokenHeader));
	}

	@PatchMapping("member/{id}/profile")
	public ResponseEntity<Boolean> updateMyProfile(@PathVariable Long id, @RequestBody UpdateMemberRequest requestBody) {
		return ResponseEntity.ok(
			authMemberService.updateProfile(id, requestBody.getUserId(), requestBody.getName(),
			requestBody.getEmail(), requestBody.getPhone())
		);
	}

	@PatchMapping("member/{id}/password")
	public ResponseEntity<Boolean> changePassword(@PathVariable Long id, @RequestBody Map<String, String> requestMap) {
		return ResponseEntity.ok(authMemberService.changePassword(id, requestMap));
	}

	@GetMapping("member/exists/user-id")
	public ResponseEntity<Boolean> checkDuplicateUserId(@RequestBody Map<String, String> requestMap) {
		return ResponseEntity.ok(authMemberService.checkDuplicate(requestMap));
	}

	@GetMapping("member/exists/email")
	public ResponseEntity<Boolean> checkDuplicateEmail(@RequestBody Map<String, String> requestMap) {
		return ResponseEntity.ok(authMemberService.checkDuplicate(requestMap));
	}
}
