package com.project.doubleshop.domain.member.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.exception.MemberNotFoundException;
import com.project.doubleshop.domain.member.entity.Member;
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

	public Member findById(Long id) {
		return Optional.of(authMemberRepository.findById(id)).orElseThrow(
			() -> new MemberNotFoundException(String.format("id [%s] NotFound", id)));
	}

	private void update(Member member) {
		authMemberRepository.save(member);
	}

	public Optional<Member> findByUserId(String userId) {
		return Optional.of(authMemberRepository.findByUserId(userId));
	}

	public Optional<Member> findByEmail(String email) {
		return Optional.of(authMemberRepository.findByEmail(email));
	}

	public Member join(String userId, String credential, String name, String email, String phone) {
		Member member = new Member(userId, passwordEncoder.encode(credential), name, email, phone);
		authMemberRepository.save(member);
		return member;
	}

	public Boolean checkDuplicate(String request) {
		if (request.equals("userId")) {
			return findByUserId(request).isPresent();
		}
		return findByEmail(request).isPresent();
	}


}
