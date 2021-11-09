package com.project.doubleshop.web.item.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.doubleshop.domain.annotation.CustomConfigureMockMvc;
import com.project.doubleshop.domain.category.service.CategoryService;
import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.service.ItemService;
import com.project.doubleshop.web.common.StatusRequest;

@SpringBootTest
@ActiveProfiles("test")
@CustomConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ItemService itemService;

	@Autowired
	private CategoryService categoryService;

	@Test
	@Order(1)
	@DisplayName("1번 상품 조회 성공")
	void findItem() throws Exception {
		mockMvc.perform(
			get("/api/item/{id}", 1)
				.accept(MediaType.APPLICATION_JSON)
		)
			.andDo(print())
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.name").exists());
	}

	@Test
	@Order(2)
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
	@Order(3)
	@DisplayName("전체 상품 리스트 조회. 1번 페이지 조회")
	void findFirstPageItem() throws Exception {
		mockMvc.perform(
			get("/api/item")
				.accept(MediaType.APPLICATION_JSON)
		)
			.andDo(print())
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$[0].id").exists());
	}

	@Test
	@Order(3)
	@DisplayName("전체 상품 리스트 조회. 2번 페이지 조회")
	void FindSecondPageItem() throws Exception {
		mockMvc.perform(
			get("/api/item")
				.param("page", "1")
				.param("size", "9")
				.accept(MediaType.APPLICATION_JSON)
		)
			.andDo(print())
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$[0].id").exists());
	}
	@Test
	@Order(4)
	@DisplayName("상품 추가 성공")
	void createNewItem() throws Exception {

		Item inputItem = Item.builder()
			.name("옷")
			.brandName("brandName")
			.description("newDescription")
			.price(1000)
			.isOnedayEligible(true)
			.isFreshEligible(true)
			.categoryId(1L)
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
	@Order(5)
	@DisplayName("10번 아이템을 삭제로 분류")
	void assignDeleteOneItem() throws Exception {

		mockMvc.perform(
			patch("/api/item/{id}/status", 10)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.param("status", Status.TO_BE_DELETED.name())
				.accept(MediaType.APPLICATION_JSON)
		).andDo(print())
			.andExpect(status().is2xxSuccessful());
	}

	@Test
	@Order(6)
	@DisplayName("2번 아이템 이름 수정")
	void editOneItem() throws Exception {
		String changedName = "changedName";
		String itemForm = objectMapper.writeValueAsString(Item.builder()
			.id(2L)
			.name(changedName)
			.description("")
			.brandName("")
			.price(10000)
			.status(Status.ACTIVATED)
			.categoryId(1L)
			.build());

		mockMvc.perform(patch("/api/item/{id}", 2)
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(itemForm)
					.accept(MediaType.APPLICATION_JSON)
		).andDo(print())
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.name").value(changedName));
	}

	@Test
	@Order(7)
	@DisplayName("9번 아이템을 삭제로 분류한 뒤, 삭제")
	void deleteOneItem() throws Exception {
		StatusRequest statusRequest = new StatusRequest(9L, Status.TO_BE_DELETED);
		itemService.updateItemStatus(statusRequest);

		mockMvc.perform(delete("/api/item")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.param("status", Status.TO_BE_DELETED.name())
				.accept(MediaType.APPLICATION_JSON)
		).andDo(print())
			.andExpect(status().is2xxSuccessful());
	}
}