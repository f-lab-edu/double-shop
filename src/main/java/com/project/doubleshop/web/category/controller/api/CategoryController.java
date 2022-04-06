package com.project.doubleshop.web.category.controller.api;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.doubleshop.domain.category.entity.Category;
import com.project.doubleshop.domain.category.service.CategoryService;
import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.item.service.ItemService;
import com.project.doubleshop.web.category.controller.dto.CategoryApiResult;
import com.project.doubleshop.web.category.controller.dto.CategoryForm;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	private final ItemService itemService;

	@PostMapping("category")
	public ResponseEntity<CategoryApiResult> newCategory(@RequestBody CategoryForm categoryForm) {
		Category category = categoryService.save(Category.convertToCategory(categoryForm));

		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.build()
			.toUri();

		return ResponseEntity.created(location).body(new CategoryApiResult(category));
	}

	@GetMapping(value = "category/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryApiResult> findCategory(@PathVariable Long id) {
		return ResponseEntity.ok(new CategoryApiResult(categoryService.findById(id)));
	}

	@GetMapping(value = "category", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoryApiResult>> findAllCategories() {
		return ResponseEntity.ok(categoryService.findAll().stream().map(CategoryApiResult::new).collect(Collectors.toList()));
	}

	@PutMapping("category")
	public ResponseEntity<CategoryApiResult> editCategory(@RequestBody CategoryForm categoryForm) {
		Category category = categoryService.save(Category.convertToCategory(categoryForm));
		return ResponseEntity.ok(new CategoryApiResult(category));
	}

	@PatchMapping("category/{id}")
	public ResponseEntity<Boolean> requestUpdateCategoryStatus(@RequestParam Status status, @PathVariable Long id) {
		return ResponseEntity.ok(categoryService.updateStatus(id, status));
	}

	@DeleteMapping("category")
	public ResponseEntity<Integer> deleteAssignedCategory(@RequestParam Status status) {
		return ResponseEntity.ok(categoryService.removeStatusDel(status));
	}
}
