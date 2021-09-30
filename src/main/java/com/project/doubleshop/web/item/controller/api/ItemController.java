package com.project.doubleshop.web.item.controller.api;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.service.ItemService;
import com.project.doubleshop.web.item.dto.ItemDTO;
import com.project.doubleshop.web.item.dto.ItemForm;
import com.project.doubleshop.web.item.dto.ItemStatusRequest;
import com.project.doubleshop.web.item.exception.InvalidItemArgumentException;
import com.project.doubleshop.web.item.exception.ItemNotFoundException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ItemController {
	private final ItemService itemService;

	@GetMapping("item/{id}")
	public ResponseEntity<ItemDTO> findItem(@PathVariable Long id) {
		return ResponseEntity.ok(
			new ItemDTO(itemService.findItem(id).orElseThrow(() -> new ItemNotFoundException(String.format("item ID[%s] not found", id)))));
	}

	@GetMapping("item")
	public ResponseEntity<List<ItemDTO>> findAllItem() {
		return ResponseEntity.ok(itemService.findItems().stream().map(ItemDTO::new).collect(Collectors.toList()));
	}

	@PostMapping(value = "item")
	public ResponseEntity<ItemDTO> newItem(@RequestBody ItemForm itemForm) {
		if(itemService.saveItem(Item.createItemInstance(itemForm))) {
			URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.build()
				.toUri();

			return ResponseEntity.created(location).body(new ItemDTO(Item.createItemInstance(itemForm)));
		} else {
			throw new InvalidItemArgumentException();
		}
	}

	@PutMapping(value = "item/{id}")
	public ResponseEntity<ItemDTO> saveItem(@RequestBody ItemForm itemForm, @PathVariable Long id) {
		itemService.findItem(id).orElseThrow(
			() -> new ItemNotFoundException(String.format("item ID[%s] not found", id))
		);
		return ResponseEntity.ok(new ItemDTO(Item.createItemInstance(itemForm)));
	}

	@PatchMapping(value = "item/{id}")
	public ResponseEntity requestUpdateItemStatus(@RequestBody ItemStatusRequest itemStatusRequest, @PathVariable Long id) {
		itemService.AssignItemStatus(itemStatusRequest);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("item")
	public ResponseEntity deleteAssignedItems(@RequestBody ItemStatusRequest itemStatusRequest) {
		itemService.DeleteAssignedItems(itemStatusRequest.getStatus());
		return ResponseEntity.ok().build();
	}
}