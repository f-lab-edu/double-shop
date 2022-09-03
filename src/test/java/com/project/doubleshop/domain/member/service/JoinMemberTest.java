package com.project.doubleshop.domain.member.service;

import static com.project.doubleshop.domain.member.service.MockMember.Member1.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.domain.member.repository.MemberRepository;
import com.project.doubleshop.web.member.dto.JoinRequest;

@ExtendWith(MockitoExtension.class)
public class JoinMemberTest {
	@Mock
	MemberRepository authMemberRepository;

	@Mock
	PasswordEncoder passwordEncoder;

	@InjectMocks
	MemberService authMemberService;

	@Test
	@DisplayName("회원가입에 필요한 입력 방식을 올바르게 입력했다면, 회원가입은 제대로 동작한다.")
	void validMemberJoinTest() {
		JoinRequest joinRequest = new JoinRequest(USER_ID, PASSWORD, NAME, EMAIL, PHONE);

		when(authMemberRepository.save(any(Member.class))).thenReturn(MEMBER);

		Member member = authMemberService.join(joinRequest);

		assertThat(MEMBER.getId()).isEqualTo(member.getId());
	}
	
	@Test
	@DisplayName("회원가입 양식에 맞지 않게 입력한 경우, 회원가입 실패")
	void invalidMemberJoinTest() {

	}
}
