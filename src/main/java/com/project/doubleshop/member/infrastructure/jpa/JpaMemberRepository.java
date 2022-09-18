package com.project.doubleshop.member.infrastructure.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.doubleshop.member.domain.Member;

public interface JpaMemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByUserId(String userId);
	Optional<Member> findByEmail(String email);
}
