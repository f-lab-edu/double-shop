package com.project.doubleshop.domain.category.repository;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.project.doubleshop.domain.category.entity.Category;
import com.project.doubleshop.domain.category.entity.CategoryType;
import com.project.doubleshop.domain.category.entity.DepthLevel;
import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.web.common.StatusRequest;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MyBatisCategoryRepositoryTest {

	@Autowired
	CategoryRepository categoryRepository;

	@Test
	@Order(1)
	@DisplayName("카테고리 생성")
	void createCategory() {
		boolean result = categoryRepository.save(Category.builder()
			.name("패션 잡화")
			.categoryType(CategoryType.CLOTH)
			.depthLevel(DepthLevel.DEPTH_ONE)
			.isRefundable(true)
			.build());

		assertThat(result).isTrue();
	}

	@Test
	@Order(2)
	@DisplayName("카테고리 pk 번호로 단건 조회")
	void findOneCategory() {
		Category category = categoryRepository.findById(1L);

		assertThat(category).isNotNull();
		assertThat(category.getId()).isNotNull();
		assertThat(category.getName()).isEqualTo("패션 잡화");
		assertThat(category.getCategoryType()).isEqualTo(CategoryType.CLOTH);
		assertThat(category.getDepthLevel()).isEqualTo(DepthLevel.DEPTH_ONE);
	}

	@Test
	@Order(3)
	@DisplayName("카테고리 전체 조회")
	void findCategories() {
		List<Category> categories = categoryRepository.findAll();

		assertThat(categories).isNotNull();
		assertThat(categories.size()).isSameAs(1);
	}

	@Test
	@Order(4)
	@DisplayName("카테고리 수정")
	void editOneCategory() {
		Category beforeEdit = categoryRepository.findById(1L);
		Category editedCategory = Category.builder()
			.id(1L)
			.name("생활용품")
			.categoryType(CategoryType.HOUSE_GOODS)
			.depthLevel(DepthLevel.DEPTH_ONE)
			.isRefundable(false)
			.build();

		boolean result = categoryRepository.save(editedCategory);
		Category afterEdit = categoryRepository.findById(1L);

		assertThat(beforeEdit).isNotNull();
		assertThat(result).isTrue();
		assertThat(beforeEdit.getId()).isEqualTo(afterEdit.getId());
		assertThat(beforeEdit.getName()).isNotEqualTo(afterEdit.getName());
	}

	@Test
	@Order(5)
	@DisplayName("카테고리 삭제 분류")
	void updateCategoryStatus() {

		int affectedRowCnt = categoryRepository.updateStatus(new StatusRequest(1L, Status.TO_BE_DELETED));

		Category category = categoryRepository.findById(1L);

		assertThat(affectedRowCnt).isSameAs(1);
		assertThat(category.getStatus()).isEqualTo(Status.TO_BE_DELETED);
	}

	@Test
	@Order(6)
	@DisplayName("삭제 분류가 된 카테고리 데이터 삭제")
	void deleteCategory() {

		int categorySize = categoryRepository.findAll().size();

		int affectedRowCnt = categoryRepository.deleteData(Status.TO_BE_DELETED);

		int afterDeletedSize = categorySize - affectedRowCnt;

		assertThat(categorySize - affectedRowCnt).isSameAs(afterDeletedSize);
	}
}