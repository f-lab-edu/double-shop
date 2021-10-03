package com.project.doubleshop.domain.member.mapper;

import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.web.member.dto.LogInRequestDto;
import com.project.doubleshop.web.member.dto.MemberInfoDto;
import com.project.doubleshop.web.member.dto.MemberSaveRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberInfoMapper {

    void insertMember(MemberSaveRequestDto requestDto);

    MemberInfoDto selectUserId(String userId);

    MemberInfoDto selectEmail(String email);

    Optional<Member> selectByUserId(String userId);

    LogInRequestDto selectByUserIdAndPassword(String userId, String password);

}
