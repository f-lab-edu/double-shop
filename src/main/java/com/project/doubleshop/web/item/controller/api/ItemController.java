package com.project.doubleshop.web.item.controller.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.service.ItemService;
import com.project.doubleshop.web.item.dto.ItemFormDTO;
import com.project.doubleshop.web.item.exception.InvalidItemArgumentException;
import com.project.doubleshop.web.item.exception.ItemNotFoundException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ItemController {
	private final ItemService itemService;

	@GetMapping("item/{id}")
	public ResponseEntity<Item> findItem(@PathVariable Long id) {

		Item item = itemService.findItem(id)
			.orElseThrow(() -> new ItemNotFoundException(String.format("item ID[%s] not found", id)));

		return ResponseEntity.ok(item);
	}

	@GetMapping("item")
	public ResponseEntity<List<Item>> findAllItem() {
		List<Item> items = itemService.findItems()
			.orElseThrow(() -> new ItemNotFoundException("item list not found"));

		return ResponseEntity.ok(items);
	}

	@PostMapping("item")
	public ResponseEntity<Item> newItem(@RequestBody ItemFormDTO itemForm) {
		if(itemService.saveItem(Item.createItemInstance(itemForm))) {
			return ResponseEntity.created(ServletUriComponentsBuilder
				.fromCurrentRequest()
				.build()
				.toUri()
			).build();
		} else {
			throw new InvalidItemArgumentException();
		}
	}

	@PutMapping("item/{id}")
	public ResponseEntity<Item> saveItem(@RequestBody ItemFormDTO itemForm, @PathVariable Long id) {
		itemService.findItem(id)
			.orElseThrow(() -> new ItemNotFoundException(String.format("item ID[%s] not found", id)));

		return ResponseEntity.ok(Item.createItemInstance(itemForm));
	}
}
