package com.project.doubleshop.member.infrastructure.myBatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.project.doubleshop.member.domain.Member;

@Mapper
public interface AuthMemberMapper {
	int insertMember(Member member);
	Member selectMemberById(Long id);
	Member selectMemberByEmail(String email);
	Member selectMemberByUserId(String userId);
	int updateMember(Member member);
	int updateMemberProfile(Member member);
	int updateMemberPassword(Member member);
}
