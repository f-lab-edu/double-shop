package com.project.doubleshop.domain.category.repository.legacy;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.doubleshop.domain.category.entity.Category;
import com.project.doubleshop.domain.category.mapper.CategoryMapper;
import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.web.common.StatusRequest;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisCategoryRepository implements CategoryRepository {

	private final CategoryMapper categoryMapper;

	@Override
	public boolean save(Category category) {
		int affectedRowCnt;
		if (category.getId() != null) {
			affectedRowCnt = categoryMapper.updateCategory(category);
		} else {
			affectedRowCnt = categoryMapper.insertCategory(category);
		}
		return affectedRowCnt != 0;
	}

	@Override
	public Category findById(Long id) {
		return categoryMapper.selectByCategoryId(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryMapper.selectAllCategories();
	}

	@Override
	public int updateStatus(StatusRequest requestDTO) {
		return categoryMapper.updateCategoryStatus(requestDTO);
	}

	@Override
	public int deleteData(Status status) {
		return categoryMapper.deleteCategory(status);
	}
}
