package com.project.doubleshop.member.domain;

import java.util.Optional;

public interface MemberRepository {
	Optional<Member> findByUserId(String userId);
	Optional<Member> findByEmail(String email);
}
