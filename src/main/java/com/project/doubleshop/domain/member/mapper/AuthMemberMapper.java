package com.project.doubleshop.domain.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.project.doubleshop.domain.member.entity.Member;

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
