package com.project.doubleshop.domain.member.repository;

import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.domain.member.mapper.*;
import com.project.doubleshop.web.member.dto.LogInRequestDto;
import com.project.doubleshop.web.member.dto.MemberInfoDto;
import com.project.doubleshop.web.member.dto.MemberSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class MyBatisMemberInfoRepository implements MemberInfoRepository {

    private final MemberInfoMapper mapper;

    @Override
    public void save(MemberSaveRequestDto requestDto) {
        mapper.insertMember(requestDto);
    }

    @Override
    public MemberInfoDto findTopOneByUserId(String userId) {
        return mapper.selectUserId(userId);
    }

    @Override
    public MemberInfoDto findTopOneByEmail(String email) {
        return mapper.selectEmail(email);
    }

    @Override
    public Optional<Member> findByUserId(String userId) {
        return mapper.selectByUserId(userId);
    }

    @Override
    public LogInRequestDto findByUserIdAndPassword(String userId, String password) {
        return mapper.selectByUserIdAndPassword(userId, password);
    }

}
