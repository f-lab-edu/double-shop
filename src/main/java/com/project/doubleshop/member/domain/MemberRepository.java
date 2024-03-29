package com.project.doubleshop.member.domain;

import java.util.Optional;

public interface MemberRepository {
	Member save(Member member);
	Optional<Member> findById(Long id);
	Optional<Member> findByUserId(String userId);
	Optional<Member> findByEmail(String email);
}
