package com.project.doubleshop.domain.mapper;

import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.web.member.dto.LogInRequestDto;
import com.project.doubleshop.web.member.dto.MemberInfoDto;
import com.project.doubleshop.web.member.dto.MemberSaveRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberInfoMapper {

    void register(MemberSaveRequestDto requestDto);

    MemberInfoDto findSameUserId(String userId);

    MemberInfoDto findSameEmail(String email);

    Optional<Member> findByUserId(String userId);

    LogInRequestDto findByUserIdAndPassword(String userId, String password);

}
