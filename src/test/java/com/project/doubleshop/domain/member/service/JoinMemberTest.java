package com.project.doubleshop.domain.member.service;

import static com.project.doubleshop.domain.member.service.MockMember.Member1.*;
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
		authMemberService.join(new JoinRequest(USER_ID, PASSWORD, NAME, EMAIL, PHONE));

		then(authMemberRepository).should(times(1)).save(any());
	}

	@Test
	@DisplayName("만약 회원가입에 필요한 파라미터들을 올바르게 입력하지 않으면, validation의 제약에 따라 IllegalArgumentException 이 발생한다 .")
	void invalidPasswordJoinTest() {
		assertThrows(IllegalArgumentException.class,
			() -> authMemberService.join(new JoinRequest(USER_ID, "only_plain_password", NAME, EMAIL, PHONE)));
	}
}
