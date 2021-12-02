package com.project.doubleshop.web.member.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.domain.member.service.AuthMemberService;
import com.project.doubleshop.web.member.dto.JoinRequest;
import com.project.doubleshop.web.member.dto.JoinResult;
import com.project.doubleshop.web.member.dto.MemberDto;
import com.project.doubleshop.web.config.security.SimpleAuthentication;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class MemberRestController {

	private final AuthMemberService authMemberService;

	@GetMapping("member/me")
	public ResponseEntity<MemberDto> me(@AuthenticationPrincipal SimpleAuthentication authentication) {
		return ResponseEntity.ok(new MemberDto(authMemberService.findById(authentication.getId())));
	}

	@PostMapping("member/join")
	public ResponseEntity<JoinResult> join(@RequestBody JoinRequest joinRequest) {
		Member member = authMemberService.join(joinRequest.getUserId(), joinRequest.getCredential(), joinRequest.getName(),
			joinRequest.getEmail(), joinRequest.getPhone());
		return ResponseEntity.ok(new JoinResult(member));
	}

	@GetMapping("member/exists/id")
	public ResponseEntity<Boolean> checkDuplicateUserId(@RequestBody Map<String, String> requestMap) {
		return ResponseEntity.ok(authMemberService.checkDuplicate(requestMap));
	}

	@GetMapping("member/exists/email")
	public ResponseEntity<Boolean> checkDuplicateEmail(@RequestBody Map<String, String> requestMap) {
		return ResponseEntity.ok(authMemberService.checkDuplicate(requestMap));
	}
}
