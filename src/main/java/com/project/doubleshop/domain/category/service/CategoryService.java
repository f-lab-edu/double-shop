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

	public Optional<Category> findCategoryById(Long categoryId) {
		return Optional.ofNullable(categoryRepository.findById(categoryId));
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
	}

	@Transactional
	public void DeleteItems(Status status) {
		categoryRepository.deleteData(status);
	}
}
