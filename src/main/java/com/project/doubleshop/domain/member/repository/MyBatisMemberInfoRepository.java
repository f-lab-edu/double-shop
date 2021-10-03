package com.project.doubleshop.domain.member.repository;

import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.domain.member.mapper.MemberInfoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class MyBatisMemberInfoRepository implements MemberInfoRepository {

    private final MemberInfoMapper mapper;

    @Override
    public void save(Member member) {
        mapper.insertMember(member.toMemberSaveRequestDto());
    }

    @Override
    public boolean existsByUserId(String userId) {
        return mapper.selectUserId(userId) != null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return mapper.selectEmail(email) != null;
    }

    @Override
    public boolean existsByUserIdAndPassword(String userId, String password) {
        return mapper.selectByUserIdAndPassword(userId, password) != null;
    }

    @Override
    public Optional<Member> findByUserId(String userId) {
        return mapper.selectByUserId(userId);
    }

}
