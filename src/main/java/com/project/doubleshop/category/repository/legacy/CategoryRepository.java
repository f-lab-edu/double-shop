package com.project.doubleshop.category.repository.legacy;

import java.util.List;

import com.project.doubleshop.category.entity.Category;
import com.project.doubleshop.common.Manageable;
import com.project.doubleshop.web.common.StatusRequest;

public interface CategoryRepository extends Manageable<StatusRequest> {

	boolean save(Category entity);

	Category findById(Long id);

	List<Category> findAll();
}
