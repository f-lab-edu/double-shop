package com.project.doubleshop.web.item.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
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
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.name").value("어반티 1+1+1 드라이 라운드 쿨티셔츠 3장세트 남여공용 기능성 반팔티 냉감 반팔 티셔츠"));
	}

	@Test
	@DisplayName("단건 상품 조회 실패")
	void failFindItem() throws  Exception {
		mockMvc.perform(
			get("/api/item/{id}", 99)
				.accept(MediaType.APPLICATION_JSON)
		)
			.andDo(print())
			.andExpect(status().is4xxClientError())
			.andExpect(jsonPath("$.id").doesNotExist())
			.andExpect(jsonPath("$.statusCode").value(HttpStatus.NOT_FOUND.getReasonPhrase()))
			.andExpect(jsonPath("$.message").exists());
	}

	@Test
	@DisplayName("전체 상품 리스트 조회")
	void findAllItem() throws Exception {
		mockMvc.perform(
			get("/api/item")
				.accept(MediaType.APPLICATION_JSON)
		)
			.andDo(print())
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$[0].id").exists());
	}
}