package com.project.doubleshop.web.item.controller.api;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.doubleshop.domain.category.entity.Category;
import com.project.doubleshop.domain.category.service.CategoryService;
import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.service.ItemService;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.item.dto.ItemApiResult;
import com.project.doubleshop.web.item.dto.ItemDTO;
import com.project.doubleshop.web.item.dto.ItemForm;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ItemRestController {

	private final ItemService itemService;

	private final CategoryService categoryService;

	@PostMapping("item")
	public ResponseEntity<ItemDTO> newItem(@RequestBody ItemForm itemForm) {
		Item item = itemService.saveItemWithCategory(Item.convertToItem(itemForm));
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.build()
			.toUri();
		return ResponseEntity.created(location).body(new ItemDTO(item));
	}

	@GetMapping("item/{id}")
	public ResponseEntity<ItemApiResult> findItem(@PathVariable Long id) {
		Item item = itemService.findItemByIdWithCategory(id);
		Category category = categoryService.findCategoryById(item.getCategoryId());
		return ResponseEntity.ok(new ItemApiResult(item, category));
	}

	@GetMapping("item")
	public ResponseEntity<List<ItemDTO>> findAllItem(Pageable pageable) {
		return ResponseEntity.ok(itemService.findItems(pageable).stream().map(ItemDTO::new).collect(Collectors.toList()));
	}



	@PatchMapping("item/{id}")
	public ResponseEntity<ItemApiResult> editItem(@RequestBody ItemForm itemForm, @PathVariable Long id) {
		Item item = itemService.saveItem(Item.convertToItem(itemForm), id);
		Category category = categoryService.findCategoryById(item.getCategoryId());
		return ResponseEntity.ok(new ItemApiResult(item, category));
	}

	@PatchMapping("item/{id}/status")
	public ResponseEntity<ItemDTO> requestUpdateItemStatus(@RequestParam Status status, @PathVariable Long id) {
		return ResponseEntity.ok(new ItemDTO(itemService.updateItemStatus(status, id)));
	}

	@DeleteMapping("item")
	public ResponseEntity deleteAssignedItems(@RequestParam Status status) {
		return ResponseEntity.ok(itemService.deleteItems(status));
	}
}