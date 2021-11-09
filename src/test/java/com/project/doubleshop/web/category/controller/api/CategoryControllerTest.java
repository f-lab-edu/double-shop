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
	@Order(5)
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
		)
			// .andDo(print())
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.name").exists())
			.andExpect(jsonPath("$.categoryType").exists())
			.andExpect(jsonPath("$.depthLevel").exists())
			.andExpect(jsonPath("$.isRefundable").exists())
		;

	}
}