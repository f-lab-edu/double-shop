package com.project.doubleshop.member.infrastructure;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.member.application.JoinRequest;
import com.project.doubleshop.member.domain.Member;
import com.project.doubleshop.member.domain.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRegisterProcessor {
	private final PasswordEncoder passwordEncoder;

	private final MemberRepository memberRepository;

	private Member member;

	@Transactional
	public void registerWith(JoinRequest requestBody) {
		Member member = Member.builder()
			.userId(requestBody.getUserId())
			.password(requestBody.getCredential())
			.name(requestBody.getName())
			.email(requestBody.getEmail())
			.phone(requestBody.getPhone())
			.build();
		member.encodePassword(passwordEncoder, member.getPassword());
		this.member = memberRepository.save(member);
	}

	public Member getResult() {
		return Optional.of(member).orElseThrow();
	}
}
