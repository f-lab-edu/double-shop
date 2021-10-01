package com.project.doubleshop.web.item.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.doubleshop.domain.annotation.CustomConfigureMockMvc;
import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.service.ItemService;
import com.project.doubleshop.web.item.dto.ItemStatusRequest;

@SpringBootTest
@ActiveProfiles("test")
@CustomConfigureMockMvc
class ItemRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ItemService itemService;

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

	@Test
	@DisplayName("상품 추가 성공")
	void createNewItem() throws Exception {
		Item inputItem = Item.builder()
			.name("newItem")
			.brandName("newBrand")
			.description("newDescription")
			.price(1)
			.build();

		String itemForm = objectMapper.writeValueAsString(inputItem);

		mockMvc.perform(
			post("/api/item")
				.contentType(MediaType.APPLICATION_JSON)
				.content(itemForm)
				.accept(MediaType.APPLICATION_JSON)
		).andDo(print())
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.name").exists());
	}

	@Test
	@DisplayName("1번 아이템을 삭제로 분류")
	void assignDeleteOneItem() throws Exception {
		String itemStatusRequest = objectMapper.writeValueAsString(new ItemStatusRequest(1L, Status.DELETED));

		mockMvc.perform(
			patch("/api/item/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(itemStatusRequest)
				.accept(MediaType.APPLICATION_JSON)
		).andDo(print())
			.andExpect(status().is2xxSuccessful());
	}

	@Test
	@DisplayName("2번 아이템 이름 수정")
	void editOneItem() throws Exception {
		String changedName = "changedName";
		String itemForm = objectMapper.writeValueAsString(Item.builder()
			.id(2L)
			.name(changedName)
			.description("")
			.brandName("")
			.price(1)
			.build());

		mockMvc.perform(put("/api/item/{id}", 2)
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(itemForm)
					.accept(MediaType.APPLICATION_JSON)
		).andDo(print())
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.name").value(changedName));
	}

	@Test
	@DisplayName("9번 아이템을 삭제로 분류한 뒤, 삭제")
	void deleteOneItem() throws Exception {
		ItemStatusRequest statusRequest = new ItemStatusRequest(9L, Status.DELETED);
		itemService.AssignItemStatus(statusRequest);

		String itemStatusRequest = objectMapper.writeValueAsString(statusRequest);

		mockMvc.perform(delete("/api/item")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(itemStatusRequest)
				.accept(MediaType.APPLICATION_JSON)
		).andDo(print())
			.andExpect(status().is2xxSuccessful());
	}
}