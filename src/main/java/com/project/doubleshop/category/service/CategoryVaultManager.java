package com.project.doubleshop.category.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.project.doubleshop.category.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class CategoryVaultManager {

	private final CategoryRepository categoryService;

	@PostConstruct
	private void warmUp() {
		categoryService.findAll();
		log.info("All Categories cached successful");
	}
}
