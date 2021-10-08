package com.project.doubleshop.domain.category.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.doubleshop.domain.category.entity.Category;

@Mapper
public interface CategoryMapper {
	int insertCategory(Category category);
	Category selectByCategoryId(Long id);
	List<Category> selectAllCategories();
	int deleteCategory(Long id);
}
