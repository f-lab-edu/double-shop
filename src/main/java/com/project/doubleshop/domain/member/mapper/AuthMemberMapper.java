package com.project.doubleshop.domain.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.project.doubleshop.domain.member.entity.v2.Member;

@Mapper
public interface AuthMemberMapper {
	int insertMember(Member member);
	Member selectMemberById(Long id);
	Member selectMemberByEmail(String email);
}
