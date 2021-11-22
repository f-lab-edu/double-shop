package com.project.doubleshop.web.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doubleshop.domain.exception.MemberNotFoundException;
import com.project.doubleshop.domain.member.service.AuthMemberService;
import com.project.doubleshop.web.security.MemberDto;
import com.project.doubleshop.web.security.SimpleAuthentication;

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
}
