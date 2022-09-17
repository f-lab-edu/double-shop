package com.project.doubleshop.domain.member.infrastructure.myBatis;

import com.project.doubleshop.domain.member.domain.Member;

public interface AuthMemberRepository {

	boolean save(Member entity);

	Member findById(Long id);

	Member findByEmail(String email);

	Member findByUserId(String userId);

	Boolean saveProfile(Member member);

	Boolean savePassword(Member build);
}
