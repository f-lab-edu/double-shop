package com.project.doubleshop.domain.member.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.doubleshop.domain.member.domain.Member;

public interface MemberRepository {
	Optional<Member> findByUserId(String userId);
	Optional<Member> findByEmail(String email);
}
