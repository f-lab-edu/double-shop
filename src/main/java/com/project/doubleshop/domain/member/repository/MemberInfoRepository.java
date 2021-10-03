package com.project.doubleshop.domain.member.repository;

import com.project.doubleshop.domain.member.entity.Member;

import java.util.Optional;

public interface MemberInfoRepository {

    void save(Member member);

    boolean existsByUserId(String userId);

    boolean existsByEmail(String email);

    boolean existsByUserIdAndPassword(String userId, String password);

    Optional<Member> findByUserId(String userId);

}
