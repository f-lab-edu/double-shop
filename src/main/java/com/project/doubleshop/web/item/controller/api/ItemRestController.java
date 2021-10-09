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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.service.ItemService;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.item.dto.ItemDTO;
import com.project.doubleshop.web.item.dto.ItemForm;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.item.exception.InvalidArgumentException;
import com.project.doubleshop.web.item.exception.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ItemRestController {
	private final ItemService itemService;

	@PostMapping("item")
	public ResponseEntity<ItemDTO> newItem(@RequestBody ItemForm itemForm) {
		if(itemService.saveItem(Item.convertToItem(itemForm))) {
			URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.build()
				.toUri();

			return ResponseEntity.created(location).body(new ItemDTO(Item.convertToItem(itemForm)));
		} else {
			throw new InvalidArgumentException();
		}
	}

	@GetMapping("item/{id}")
	public ResponseEntity<ItemDTO> findItem(@PathVariable Long id) {
		return ResponseEntity.ok(
			new ItemDTO(itemService.findItemById(id).orElseThrow(() -> new DataNotFoundException(String.format("item ID[%s] not found", id)))));
	}

	@GetMapping("item")
	public ResponseEntity<List<ItemDTO>> findAllItem(Pageable pageable) {
		return ResponseEntity.ok(itemService.findItems(pageable).stream().map(ItemDTO::new).collect(Collectors.toList()));
	}



	@PutMapping("item/{id}")
	public ResponseEntity<ItemDTO> editItem(@RequestBody ItemForm itemForm, @PathVariable Long id) {
		Item item = itemService.saveItem(Item.convertToItem(itemForm), id);
		return ResponseEntity.ok(new ItemDTO(item));
	}

	@PatchMapping("item/{id}")
	public ResponseEntity<ItemDTO> requestUpdateItemStatus(@RequestParam Status status, @PathVariable Long id) {
		return ResponseEntity.ok(new ItemDTO(itemService.updateItemStatus(status, id)));
	}

	@DeleteMapping("item")
	public ResponseEntity deleteAssignedItems(@RequestParam Status status) {
		return ResponseEntity.ok(itemService.deleteItems(status));
	}
}