package com.project.doubleshop.member.infrastructure;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.project.doubleshop.exception.NotFoundException;
import com.project.doubleshop.member.domain.Member;
import com.project.doubleshop.member.domain.MemberRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberCrudService {
	private final MemberRepository memberRepository;

	public Member findById(Long id) {
		return memberRepository.findById(id)
			.orElseThrow(() -> new NotFoundException(String.format("Id [%d] NotFound", id)));
	}

	public boolean isUserIdExisted(String userId) {
		return memberRepository.findByUserId(userId).isPresent();
	}

	public boolean isEmailExisted(String email) {
		return memberRepository.findByEmail(email).isPresent();
	}
}
