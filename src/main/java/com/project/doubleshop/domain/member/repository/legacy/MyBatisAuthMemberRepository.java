package com.project.doubleshop.domain.member.repository.legacy;

import org.springframework.stereotype.Repository;

import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.domain.member.mapper.AuthMemberMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisAuthMemberRepository implements AuthMemberRepository {

	private final AuthMemberMapper mapper;

	@Override
	public boolean save(Member member) {

		int affectedRowCnt;

		if (member.getId() != null) {
			affectedRowCnt = mapper.updateMember(member);
		} else {
			affectedRowCnt = mapper.insertMember(member);
		}

		return affectedRowCnt != 0;
	}

	@Override
	public Member findById(Long id) {
		return mapper.selectMemberById(id);
	}

	@Override
	public Member findByEmail(String email) {
		return mapper.selectMemberByEmail(email);
	}

	@Override
	public Member findByUserId(String userId) {
		return mapper.selectMemberByUserId(userId);
	}

	@Override
	public Boolean saveProfile(Member member) {
		return mapper.updateMemberProfile(member) == 1;
	}

	@Override
	public Boolean savePassword(Member member) {
		return mapper.updateMemberPassword(member) == 1;
	}

}
