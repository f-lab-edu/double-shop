package com.project.doubleshop.domain.category.repository;

import java.util.List;

import com.project.doubleshop.domain.category.entity.Category;
import com.project.doubleshop.domain.common.Manageable;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.web.common.StatusRequest;

public interface CategoryRepository extends Manageable<StatusRequest> {

	boolean save(Category entity);

	Category findById(Long id);

	List<Category> findAll();
}
