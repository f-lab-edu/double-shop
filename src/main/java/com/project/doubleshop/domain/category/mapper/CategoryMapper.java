package com.project.doubleshop.domain.category.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.doubleshop.domain.category.entity.Category;
import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.web.common.StatusRequest;

@Mapper
public interface CategoryMapper {
	int insertCategory(Category category);
	Category selectByCategoryId(Long id);
	List<Category> selectAllCategories();
	int updateCategory(Category category);
	int updateCategoryStatus(StatusRequest statusRequest);
	int deleteCategory(Status status);
}
