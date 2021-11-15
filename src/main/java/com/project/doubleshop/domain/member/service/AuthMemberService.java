package com.project.doubleshop.domain.member.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.exception.MemberNotFoundException;
import com.project.doubleshop.domain.member.entity.v2.Member;
import com.project.doubleshop.domain.member.repository.AuthMemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthMemberService {

	private final AuthMemberRepository authMemberRepository;

	private final PasswordEncoder passwordEncoder;

	@Transactional
	public Member login(String userId, String password) {
		Member member = findByUserId(userId).orElseThrow(
			() -> new MemberNotFoundException(String.format("userId [%s] NotFound", userId)));
		member.login(passwordEncoder, password);
		member.afterSuccessLogin();
		update(member);
		return member;
	}

	private void update(Member member) {
		authMemberRepository.save(member);
	}

	public Optional<Member> findByUserId(String userId) {
		return Optional.of(authMemberRepository.findByUserId(userId));
	}
}
