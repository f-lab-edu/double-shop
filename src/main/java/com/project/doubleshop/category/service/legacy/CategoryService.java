package com.project.doubleshop.category.service.legacy;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.category.entity.Category;
import com.project.doubleshop.category.repository.legacy.CategoryRepository;
import com.project.doubleshop.common.Status;
import com.project.doubleshop.exception.NotFoundException;
import com.project.doubleshop.web.common.StatusRequest;

import lombok.RequiredArgsConstructor;

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
			throw new NotFoundException(String.format("Inserted category id %d not found", category.getId()));
		}
	}

	@Transactional
	public Category saveCategory(Category category, Long categoryId) {
		findCategoryById(categoryId);
		categoryRepository.save(category);
		return categoryRepository.findById(categoryId);
	}

	public Category findCategoryById(Long categoryId) {
		return Optional.ofNullable(categoryRepository.findById(categoryId)).orElseThrow(() -> new NotFoundException(String.format("category ID '%s' not found", categoryId)));
	}

	public List<Category> findCategories() {
		return categoryRepository.findAll();
	}

	@Transactional
	public void updateCategoryStatus(StatusRequest requestDTO) {
		Category category = categoryRepository.findById(requestDTO.getId());
		if (category == null) {
			throw new NotFoundException(String.format("category ID '%s' not found", requestDTO.getId()));
		}
		if (Status.of(requestDTO.getStatus().name()) == null) {
			throw new NotFoundException(String.format("request status value '%s' not found", requestDTO.getStatus().name()));
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
