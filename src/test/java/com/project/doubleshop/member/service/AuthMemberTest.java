package com.project.doubleshop.member.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.doubleshop.member.application.MemberFacade;
import com.project.doubleshop.member.presentation.MemberAuthApi;
import com.project.doubleshop.member.presentation.request.AuthenticationRequest;

@DisplayName("회원 인증 API")
@ExtendWith(MockitoExtension.class)
public class AuthMemberTest {

	@Mock
	MemberFacade memberFacade;
	@InjectMocks
	MemberAuthApi memberAuthApi;
	private MockMvc mockMvc;
	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(memberAuthApi)
			.build();
		objectMapper = new ObjectMapper();
	}

	@Nested
	@DisplayName("회원 인증 api 테스트")
	final class MemberAuthApiRequestTest {

		@ParameterizedTest
		@NullAndEmptySource
		@DisplayName("로그인 테스트")
		void validMemberLogin(String arg) throws Exception {
			AuthenticationRequest authReq = new AuthenticationRequest("wanni1234", "a!sdf#$@Sdfjg#$");
			ResultActions actions = mockMvc.perform(
				post("/api/v2/auth")
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(authReq))
			);

			actions
				.andExpect(status().is2xxSuccessful());
		}
	}
}
