package com.project.doubleshop.domain.member.repository;

import com.project.doubleshop.domain.member.entity.Member;

public interface AuthMemberRepository {

	boolean save(Member entity);

	Member findById(Long id);

	Member findByEmail(String email);

	Member findByUserId(String userId);


}
