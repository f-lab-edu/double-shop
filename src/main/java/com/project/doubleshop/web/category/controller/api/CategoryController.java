package com.project.doubleshop.web.category.controller.api;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import com.project.doubleshop.domain.common.mapper.param.RequestItemsWithCategory;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.service.ItemService;
import com.project.doubleshop.web.category.controller.dto.CategoryDTO;
import com.project.doubleshop.web.category.controller.dto.CategoryForm;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.item.dto.ItemApiResult;
import com.project.doubleshop.web.item.exception.DataNotFoundException;
import com.project.doubleshop.web.item.exception.InvalidArgumentException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	private final ItemService itemService;

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
	public ResponseEntity<List<ItemApiResult>> findCategoryWithItems(@PathVariable Long id, Pageable pageable) {

		List<Item> itemsWithCategory = itemService.findItemsWithCategory(
			new RequestItemsWithCategory(id, pageable.page(), pageable.size()));

		Category category = categoryService.findCategoryById(id);

		return ResponseEntity.ok(
			itemsWithCategory.stream().map(item -> new ItemApiResult(item, category)).collect(Collectors.toList()));
	}

	@GetMapping("category")
	public ResponseEntity<List<CategoryDTO>> findAllCategories() {
		return ResponseEntity.ok(categoryService.findCategories().stream().map(CategoryDTO::new).collect(Collectors.toList()));
	}

	@PutMapping("category/{id}")
	public ResponseEntity<CategoryDTO> editCategory(@RequestBody CategoryForm categoryForm, @PathVariable Long id) {
		Category category = categoryService.saveCategory(Category.convertToCategory(categoryForm), id);
		return ResponseEntity.ok(new CategoryDTO(category));
	}

	@PatchMapping("category/{id}")
	public ResponseEntity<CategoryDTO> requestUpdateCategoryStatus(@RequestParam Status status, @PathVariable Long id) {
		return ResponseEntity.ok(new CategoryDTO(categoryService.updateCategoryStatus(status, id)));
	}

	@DeleteMapping("category")
	public ResponseEntity deleteAssignedCategory(@RequestParam Status status) {
		return ResponseEntity.ok(categoryService.deleteCategories(status));
	}
}
