package com.project.doubleshop.domain.member.repository;

import com.project.doubleshop.domain.member.entity.Member;

import java.util.Optional;

@Deprecated
public interface MemberInfoRepository {
    @Deprecated
    void save(Member member);

    boolean existsByUserId(String userId);

    boolean existsByEmail(String email);

    @Deprecated
    boolean existsByUserIdAndPassword(String userId, String password);

    @Deprecated
    Optional<Member> findByUserId(String userId);

}
