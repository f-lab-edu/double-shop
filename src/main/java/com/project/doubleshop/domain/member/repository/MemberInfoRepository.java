package com.project.doubleshop.domain.member.repository;

import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.web.member.dto.LogInRequestDto;
import com.project.doubleshop.web.member.dto.MemberInfoDto;
import com.project.doubleshop.web.member.dto.MemberSaveRequestDto;

import java.util.Optional;

public interface MemberInfoRepository {

    void save(MemberSaveRequestDto requestDto);

    MemberInfoDto findTopOneByUserId(String userId);

    MemberInfoDto findTopOneByEmail(String email);

    Optional<Member> findByUserId(String userId);

    LogInRequestDto findByUserIdAndPassword(String userId, String password);

}
