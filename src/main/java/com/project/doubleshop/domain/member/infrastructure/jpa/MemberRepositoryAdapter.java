package com.project.doubleshop.domain.member.infrastructure.jpa;

import java.util.Optional;

import com.project.doubleshop.domain.member.domain.Member;
import com.project.doubleshop.domain.member.domain.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryAdapter implements MemberRepository {

	private final JpaMemberRepository jpaMemberRepository;

	@Override
	public Optional<Member> findByUserId(String userId) {
		return jpaMemberRepository.findByUserId(userId);
	}

	@Override
	public Optional<Member> findByEmail(String email) {
		return jpaMemberRepository.findByEmail(email);
	}
}
