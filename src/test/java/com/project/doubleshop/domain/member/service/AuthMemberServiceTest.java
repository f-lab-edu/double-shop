package com.project.doubleshop.domain.member.service;

import static com.project.doubleshop.domain.member.service.MockMember.Member1.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.doubleshop.domain.exception.NotFoundException;
import com.project.doubleshop.domain.member.repository.legacy.AuthMemberRepository;
import com.project.doubleshop.domain.member.service.legacy.AuthMemberService;

@ExtendWith(MockitoExtension.class)
class AuthMemberServiceTest {

	@Mock
	AuthMemberRepository authMemberRepository;

	@Mock
	PasswordEncoder passwordEncoder;

	@InjectMocks
	AuthMemberService authMemberService;

	@Test
	@DisplayName("로그인 로직 동작 테스트")
	void loginTest() {
		given(authMemberRepository.findByUserId(any())).willReturn(MEMBER);
		given(passwordEncoder.matches(PASSWORD, PASSWORD)).willReturn(true);

		authMemberService.login(USER_ID, PASSWORD);

		then(authMemberRepository).should(times(1)).save(MEMBER);
	}

	@Test
	@DisplayName("로그인 실패 - 없는 계정 입력")
	void loginFailTest() {
		given(authMemberRepository.findByUserId(any())).willReturn(null);

		assertThrows(NotFoundException.class, () -> authMemberService.login(USER_ID, PASSWORD));
	}

	@Test
	@DisplayName("로그인 실패 - 잘못된 비밀번호 입력")
	void loginWithWrongPassword() {
		given(authMemberRepository.findByUserId(any())).willReturn(MEMBER);
		given(passwordEncoder.matches(PASSWORD, PASSWORD)).willReturn(false);

		assertThrows(IllegalArgumentException.class, () -> authMemberService.login(USER_ID, PASSWORD));
	}

	@Test
	@DisplayName("이메일로 사용자 검색 실패")
	void notFoundEmail() {
		given(authMemberRepository.findByEmail(any())).willReturn(null);

		assertThat(authMemberService.findByEmail(EMAIL)).isEmpty();
	}

	@Test
	@DisplayName("회원가입 로직 동작 테스트")
	void joinTest() {
		authMemberService.join(USER_ID, PASSWORD, NAME, EMAIL, PHONE);

		then(authMemberRepository).should(times(1)).save(any());
	}

	@Test
	@DisplayName("이메일 중복 동작 테스트")
	void emailTest() {
		authMemberService.findByEmail(EMAIL);

		then(authMemberRepository).should(times(1)).findByEmail(EMAIL);
	}
}