package com.project.doubleshop.web.item.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.project.doubleshop.domain.annotation.CustomConfigureMockMvc;

@SpringBootTest
@ActiveProfiles("test")
@CustomConfigureMockMvc
class ItemControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("1번 상품 조회 성공")
	void findItem() throws Exception {
		mockMvc.perform(
			get("/api/item/{id}", 1)
				.accept(MediaType.APPLICATION_JSON)
		)
			.andDo(print())
			.andExpect(status().is2xxSuccessful());
	}

	@Test
	@DisplayName("단건 상품 조회 실패")
	void failFindItem() throws  Exception {
		mockMvc.perform(
			get("/api/item/{id}", 99)
				.accept(MediaType.APPLICATION_JSON)
		)
			.andDo(print())
			.andExpect(status().is4xxClientError());
	}

	@Test
	@DisplayName("전체 상품 리스트 조회")
	void findAllItem() throws Exception {
		mockMvc.perform(
			get("/api/item")
				.accept(MediaType.APPLICATION_JSON)
		)
			.andDo(print())
			.andExpect(status().is2xxSuccessful());
	}
}