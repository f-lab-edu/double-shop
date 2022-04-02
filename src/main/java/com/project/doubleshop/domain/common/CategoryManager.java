package com.project.doubleshop.domain.common;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import com.project.doubleshop.domain.category.entity.Category;
import com.project.doubleshop.domain.category.service.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryManager {

	private final CategoryService categoryService;

	private final CacheManager cacheManager;

	@PostConstruct
	private void warmUp() {
		List<Category> categories = categoryService.findAll();
		Cache categoryCache = cacheManager.getCache("category");
		categories.forEach(c -> {
			assert categoryCache != null;
			categoryCache.put(c.getId(), c);
		});
		log.info("cache category");
	}
}
