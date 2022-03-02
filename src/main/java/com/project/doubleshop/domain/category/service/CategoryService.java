package com.project.doubleshop.domain.category.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.category.entity.Category;
import com.project.doubleshop.domain.category.repository.CategoryRepository;
import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.exception.NotFoundException;
import com.project.doubleshop.domain.utils.ExceptionUtils;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {
	private final CategoryRepository categoryRepository;

	@Transactional
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	public Category findById(Long categoryId) {
		return categoryRepository.findById(categoryId)
			.orElseThrow(() -> new NotFoundException(String.format("Category ID '%s' not found.", categoryId)));
	}

	public List<Category> findAll() {
		return categoryRepository.findAllByStatus(Status.ACTIVATED);
	}

	public List<Category> findAllByIds(List<Long> categoryIds) {
		List<Category> categories = categoryRepository.findAllById(categoryIds);

		if (categoryIds.size() != categories.size()) {
			Set<Long> validIds = categories
				.stream()
				.map(Category::getId)
				.collect(Collectors.toSet());

			List<Long> invalidIds = categoryIds
				.stream()
				.filter(id -> !validIds.contains(id))
				.collect(Collectors.toList());

			ExceptionUtils.findInvalidIdsAndThrow404Error(invalidIds, "Invalid category id");
		}
		return categories;
	}

	@Transactional
	public Boolean updateStatus(Long categoryId, Status status) {
		Category category = findById(categoryId);
		Status previous = category.getStatus();
		category.saveStatus(status);
		return !previous.equals(category.getStatus());
	}

	@Transactional
	public Integer removeStatusDel(Status status) {
		List<Long> ids = categoryRepository.findIdsByStatus(status.getValue());
		categoryRepository.deleteAllById(ids);
		return ids.size();
	}
}
