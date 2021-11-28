package com.project.doubleshop.security;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.doubleshop.domain.annotation.CustomConfigureMockMvc;
import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.domain.member.service.AuthMemberService;
import com.project.doubleshop.web.member.dto.AuthenticationRequest;
import com.project.doubleshop.web.member.dto.JoinRequest;


@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@CustomConfigureMockMvc
public class AuthTest {

	@Autowired
	AuthMemberService authMemberService;

	@Autowired
	ObjectMapper objectMapper;

	private JoinRequest joinRequest;

	private AuthenticationRequest authenticationRequest;

	@BeforeAll
	public void setUp() {
		joinRequest = new JoinRequest("userId123", "password123", "userName", "email@gmail.com",
			"010-1234-5678");

		authenticationRequest = new AuthenticationRequest("userId123", "password123");
	}

	@Test
	@Order(1)
	void joinTest() throws Exception {

		Member member = authMemberService.join(joinRequest.getUserId(), joinRequest.getCredential(),
			joinRequest.getName(),
			joinRequest.getEmail(), joinRequest.getPhone());

		assertThat(member).isNotNull();
		assertThat(member.getId()).isNotNull();
	}

	@Test
	@Order(2)
	void loginTest() {
		Member member = authMemberService.login(authenticationRequest.getPrincipal(),
			authenticationRequest.getCredential());

		assertThat(member.getId()).isNotNull();
		assertThat(member.getCount()).isNotNull();
	}
}
