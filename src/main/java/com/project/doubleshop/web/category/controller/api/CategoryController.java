package com.project.doubleshop.web.category.controller.api;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.doubleshop.domain.category.entity.Category;
import com.project.doubleshop.domain.category.service.CategoryService;
import com.project.doubleshop.web.category.controller.dto.CategoryDTO;
import com.project.doubleshop.web.category.controller.dto.CategoryForm;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.item.exception.DataNotFoundException;
import com.project.doubleshop.web.item.exception.InvalidArgumentException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	@PostMapping("category")
	public ResponseEntity<CategoryDTO> newCategory(@RequestBody CategoryForm categoryForm) {
		if (categoryService.saveCategory(Category.convertToCategory(categoryForm))) {
			URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.build()
				.toUri();

			return ResponseEntity.created(location).body(new CategoryDTO(Category.convertToCategory(categoryForm)));
		} else {
			throw new InvalidArgumentException();
		}
	}

	@GetMapping("category/{id}")
	public ResponseEntity<CategoryDTO> findCategory(@PathVariable Long id) {
		return ResponseEntity.ok(
			new CategoryDTO(categoryService.findCategoryById(id).orElseThrow(() ->
				new DataNotFoundException(String.format("category ID[%s] not found", id))))
		);
	}

	@GetMapping("category")
	public ResponseEntity<List<CategoryDTO>> findAllCategories() {
		return ResponseEntity.ok(categoryService.findCategories().stream().map(CategoryDTO::new).collect(Collectors.toList()));
	}

	@PutMapping("category/{id}")
	public ResponseEntity editCategory(@RequestBody CategoryForm categoryForm, @PathVariable Long id) {
		categoryService.findCategoryById(id).orElseThrow(
			() -> new DataNotFoundException(String.format("category ID[%s] not found", id))
		);
		return ResponseEntity.ok(new CategoryDTO(Category.convertToCategory(categoryForm)));
	}

	@PatchMapping("category/{id}")
	public ResponseEntity requestUpdateCategoryStatus(@RequestBody StatusRequest statusRequest, @PathVariable Long id) {
		categoryService.updateCategoryStatus(statusRequest);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("category")
	public ResponseEntity deleteAssignedCategory(@RequestBody StatusRequest statusRequest) {
		categoryService.deleteItems(statusRequest.getStatus());
		return ResponseEntity.ok().build();
	}
}
