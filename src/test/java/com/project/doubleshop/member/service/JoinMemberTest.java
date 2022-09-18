package com.project.doubleshop.member.service;

import static com.project.doubleshop.member.service.MockMember.Member1.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.doubleshop.exception.DuplicateMemberException;
import com.project.doubleshop.member.application.MemberService;
import com.project.doubleshop.member.domain.Member;
import com.project.doubleshop.member.infrastructure.jpa.JpaMemberRepository;
import com.project.doubleshop.member.application.JoinRequest;

@ExtendWith(MockitoExtension.class)
public class JoinMemberTest {

	private final static int MIN = 10;
	private final static int MAX = 50;

	private String passwordViolationMessage = String.format("\n"
		+ "Password must be between %d and %d characters.", MIN, MAX);

	@Mock
	JpaMemberRepository memberRepository;

	@Mock
	PasswordEncoder passwordEncoder;

	@InjectMocks
	MemberService authMemberService;

	@Test
	@DisplayName("회원가입에 필요한 입력 방식을 올바르게 입력했다면, 회원가입은 제대로 동작한다.")
	void validMemberJoinTest() {
		JoinRequest joinRequest = new JoinRequest(USER_ID, PASSWORD, NAME, EMAIL, PHONE);

		when(memberRepository.findByUserId(USER_ID)).thenReturn(Optional.empty());
		when(memberRepository.findByEmail(EMAIL)).thenReturn(Optional.empty());
		when(memberRepository.save(any(Member.class))).thenReturn(MEMBER);

		Member member = authMemberService.join(joinRequest);
		boolean isDuplicated = authMemberService.checkDuplicated(USER_ID, EMAIL);

		assertThat(isDuplicated).isFalse();
		assertThat(MEMBER.getId()).isEqualTo(member.getId());
	}

	@Test
	@DisplayName("이미 존재하는 아이디로 회원가입을 하려고 한다면, 회원가입은 실패한다.")
	void invalidMemberJoinTest() {
		JoinRequest joinRequest = new JoinRequest(USER_ID, PASSWORD, NAME, EMAIL, PHONE);

		when(memberRepository.findByUserId(USER_ID)).thenReturn(Optional.ofNullable(MEMBER));

		assertThrows(DuplicateMemberException.class, () -> authMemberService.join(joinRequest));
	}

	@Test
	@DisplayName("회원가입 양식에 맞지 않게 입력한 경우, validation 에 위반하는 메세지를 받는다.")
	void memberJoinValidationTest() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		JoinRequest joinRequest = new JoinRequest(USER_ID, "qwe", NAME, EMAIL, PHONE);

		List<ConstraintViolation<JoinRequest>> violations = new ArrayList<>(validator.validate(joinRequest));

		assertThat(violations).isNotEmpty();
		assertThat(violations.get(0).getMessage()).isEqualTo(passwordViolationMessage);
	}
}
