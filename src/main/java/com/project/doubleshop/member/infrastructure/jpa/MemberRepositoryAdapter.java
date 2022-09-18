package com.project.doubleshop.member.infrastructure.jpa;

import java.util.Optional;

import com.project.doubleshop.member.domain.Member;
import com.project.doubleshop.member.domain.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryAdapter implements MemberRepository {

	private final JpaMemberRepository jpaMemberRepository;

	@Override
	public Member save(Member member) {
		return jpaMemberRepository.save(member);
	}

	@Override
	public Optional<Member> findByUserId(String userId) {
		return jpaMemberRepository.findByUserId(userId);
	}

	@Override
	public Optional<Member> findByEmail(String email) {
		return jpaMemberRepository.findByEmail(email);
	}
}
