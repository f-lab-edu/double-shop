package com.project.doubleshop.domain.category.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.category.entity.Category;
import com.project.doubleshop.domain.category.repository.CategoryRepository;
import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.item.exception.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;

	@Transactional
	public boolean saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Transactional
	public Category getInsertedCategory(Category category) {
		if (saveCategory(category)) {
			return category;
		} else {
			throw new DataNotFoundException(String.format("inserted category id %d not found", category.getId()));
		}
	}

	@Transactional
	public Category saveCategory(Category category, Long categoryId) {
		findCategoryById(categoryId);
		categoryRepository.save(category);
		return categoryRepository.findById(categoryId);
	}

	public Category findCategoryById(Long categoryId) {
		return Optional.ofNullable(categoryRepository.findById(categoryId)).orElseThrow(() -> new DataNotFoundException(String.format("category ID '%s' not found", categoryId)));
	}

	public List<Category> findCategories() {
		return categoryRepository.findAll();
	}

	@Transactional
	public void updateCategoryStatus(StatusRequest requestDTO) {
		Category category = categoryRepository.findById(requestDTO.getId());
		if (category == null) {
			throw new DataNotFoundException(String.format("category ID '%s' not found", requestDTO.getId()));
		}
		if (Status.of(requestDTO.getStatus().name()) == null) {
			throw new IllegalArgumentException(String.format("request status value '%s' not found", requestDTO.getStatus().name()));
		}
		categoryRepository.updateStatus(requestDTO);
	}

	@Transactional
	public Category updateCategoryStatus(Status status, Long categoryId) {
		updateCategoryStatus(new StatusRequest(categoryId, status));
		return findCategoryById(categoryId);
	}

	@Transactional
	public Integer deleteCategories(Status status) {
		return categoryRepository.deleteData(status);
	}
}
