package com.project.doubleshop.domain.category.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import com.project.doubleshop.domain.category.entity.Category;
import com.project.doubleshop.domain.category.repository.CategoryRepository;
import com.project.doubleshop.domain.category.service.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CategoryVaultManager {

	private final CategoryRepository categoryService;

	private final Cache cache;

	private final List<Category> cachedCategories;

	public CategoryVaultManager(CategoryRepository categoryService, @Qualifier("categoryCache") Cache cache) {
		this.categoryService = categoryService;
		this.cache = cache;
		this.cachedCategories = new ArrayList<>();
	}

	@PostConstruct
	private void warmUp() {
		List<Category> categories = categoryService.findAll();
		categories.forEach(c -> {
			addCategoryCache(c);
			cachedCategories.add(c);
		});
		log.info("All Categories cached successful");
	}

	public void addCategoryCache(Category category) {
		cache.putIfAbsent(category.getId(), category);
	}

	public Category findCachedCategory(Long categoryId) {
		return cache.get(categoryId, Category.class);
	}

	public List<Category> findCachedCategories() {
		return this.cachedCategories;
	}
}
