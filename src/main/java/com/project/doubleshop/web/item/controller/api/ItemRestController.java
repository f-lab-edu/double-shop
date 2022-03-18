package com.project.doubleshop.web.item.controller.api;

import static com.project.doubleshop.web.common.file.ImageFile.*;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.service.ItemService;
import com.project.doubleshop.web.common.file.client.FileClient;
import com.project.doubleshop.web.item.dto.ItemApiResult;
import com.project.doubleshop.web.item.dto.ItemForm;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ItemRestController {

	private final ItemService itemService;

	private final FileClient fileClient;

	@PostMapping(value = "item", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<ItemApiResult> newItem(@RequestPart ItemForm itemForm, @RequestPart(required = false)
		MultipartFile[] imageFiles) {
		Item item = itemService.save(itemForm);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.build()
			.toUri();

		String path = location.getPath() + "/" + item.getId();

		if (imageFiles != null) {
			Arrays.asList(imageFiles).forEach(file -> uploadImageFile(fileClient, of(file), path));
		}

		return ResponseEntity.created(location).body(new ItemApiResult(item));
	}

	@GetMapping("item/{id}")
	public ResponseEntity<ItemApiResult> findItem(@PathVariable Long id) {
		Item item = itemService.findById(id);
		return ResponseEntity.ok(new ItemApiResult(item));
	}

	@GetMapping("item")
	public ResponseEntity<List<ItemApiResult>> findAllItem(Pageable pageable) {
		return ResponseEntity.ok(itemService.findItems(pageable).stream().map(ItemApiResult::new).collect(Collectors.toList()));
	}

	@PutMapping("item")
	public ResponseEntity<ItemApiResult> editItem(@RequestBody ItemForm itemForm) {
		Item item = itemService.save(itemForm);
		return ResponseEntity.ok(new ItemApiResult(item));
	}

	@PatchMapping("item/{id}/status")
	public ResponseEntity<Boolean> requestUpdateItemStatus(@RequestParam Status status, @PathVariable Long id) {
		return ResponseEntity.ok(itemService.updateStatus(id, status));
	}

	@DeleteMapping("item")
	public ResponseEntity<Integer> deleteAssignedItems(@RequestParam Status status) {
		return ResponseEntity.ok(itemService.removeStatusDel(status));
	}
}