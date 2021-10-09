package com.project.doubleshop.web.category.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.doubleshop.domain.annotation.CustomConfigureMockMvc;
import com.project.doubleshop.domain.category.entity.Category;
import com.project.doubleshop.domain.category.entity.CategoryType;
import com.project.doubleshop.domain.category.entity.DepthLevel;
import com.project.doubleshop.domain.category.service.CategoryService;
import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.web.category.controller.dto.CategoryForm;

@SpringBootTest
@ActiveProfiles("test")
@CustomConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private CategoryService categoryService;

	@Test
	@Order(1)
	@DisplayName("카테고리 추가 성공")
	void newCategory() throws Exception {

		String categoryForm = objectMapper.writeValueAsString(new CategoryForm(Category.builder()
			.name("패션 잡화")
			.categoryType(CategoryType.CLOTH)
			.depthLevel(DepthLevel.DEPTH_ONE)
			.isRefundable(true)
			.status(Status.ACTIVATED)
			.statusUpdateTime(LocalDateTime.now())
			.build()));

		mockMvc.perform(
			post("/api/category")
				.contentType(MediaType.APPLICATION_JSON)
				.content(categoryForm)
				.accept(MediaType.APPLICATION_JSON)
		).andDo(print())
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.name").exists())
			.andExpect(jsonPath("$.categoryType").exists())
			.andExpect(jsonPath("$.depthLevel").exists())
			.andExpect(jsonPath("$.isRefundable").exists())
			.andExpect(jsonPath("$.status").exists())
			.andExpect(jsonPath("$.statusUpdateTime").exists())
		;

	}

	@Test
	@Order(2)
	@DisplayName("카테고리 단건 조회 성공")
	void findCategory() throws Exception {
		Category category = categoryService.findCategoryById(1L).orElseGet(() ->
			Category.builder()
				.name("패션 잡화")
				.categoryType(CategoryType.CLOTH)
				.depthLevel(DepthLevel.DEPTH_ONE)
				.isRefundable(true)
				.status(Status.ACTIVATED)
				.statusUpdateTime(LocalDateTime.now())
				.build()
		);

		categoryService.saveCategory(category);

		mockMvc.perform(
			get("/api/category/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
		).andDo(print())
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.name").exists())
			.andExpect(jsonPath("$.categoryType").exists())
			.andExpect(jsonPath("$.depthLevel").exists())
			.andExpect(jsonPath("$.isRefundable").exists())
			.andExpect(jsonPath("$.status").exists())
			.andExpect(jsonPath("$.statusUpdateTime").exists())
		;
	}

	@Test
	@Order(3)
	@DisplayName("카테고리 목록 조회 성공")
	void findAllCategories() throws Exception {

		mockMvc.perform(
			get("/api/category")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
		).andDo(print())
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$[0].name").exists())
			.andExpect(jsonPath("$[0].categoryType").exists())
			.andExpect(jsonPath("$[0].depthLevel").exists())
			.andExpect(jsonPath("$[0].isRefundable").exists())
			.andExpect(jsonPath("$[0].status").exists())
			.andExpect(jsonPath("$[0].statusUpdateTime").exists())
		;
	}

	@Test
	@Order(4)
	@DisplayName("카테고리 수정 성공")
	void editCategory() throws Exception {
		String categoryForm = objectMapper.writeValueAsString(new CategoryForm(Category.builder()
			.id(1L)
			.name("패션 잡화2")
			.categoryType(CategoryType.CLOTH)
			.depthLevel(DepthLevel.DEPTH_THREE)
			.isRefundable(false)
			.status(Status.ACTIVATED)
			.statusUpdateTime(LocalDateTime.now())
			.build()));

		mockMvc.perform(
			put("/api/category/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(categoryForm)
				.accept(MediaType.APPLICATION_JSON)
		).andDo(print())
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.name").value("패션 잡화2"))
			.andExpect(jsonPath("$.categoryType").exists())
			.andExpect(jsonPath("$.depthLevel").exists())
			.andExpect(jsonPath("$.isRefundable").exists())
			.andExpect(jsonPath("$.status").exists())
			.andExpect(jsonPath("$.statusUpdateTime").exists())
			;
	}

	@Test
	@Order(5)
	@DisplayName("카테고리 삭제 요청 성공")
	void requestUpdateCategoryStatus() throws Exception {
		mockMvc.perform(
			patch("/api/category/{id}", 1)
				.param("status", Status.TO_BE_DELETED.name())
		).andDo(print())
			.andExpect(status().is2xxSuccessful())
		;
	}

	@Test
	@Order(6)
	@DisplayName("카테고리 삭제 성공")
	void deleteAssignedCategory() throws Exception {
		mockMvc.perform(
			delete("/api/category")
				.param("status", Status.TO_BE_DELETED.name())
		).andDo(print())
			.andExpect(status().is2xxSuccessful())
		;
	}
}