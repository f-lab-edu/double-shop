package com.project.doubleshop.web.item.controller.api;

import static com.project.doubleshop.web.common.file.ImageFile.*;

import java.net.URI;
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

import com.project.doubleshop.common.Status;
import com.project.doubleshop.item.entity.Item;
import com.project.doubleshop.item.service.ItemService;
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

	@PostMapping(value = "member/{memberId}/item", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<ItemApiResult> newItem(@RequestPart ItemForm itemForm, @RequestPart(required = false)
		MultipartFile imageFile) {
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.build()
			.toUri();

		String imageUrl = uploadAsyncImageFile(fileClient, of(imageFile), null);

		Item item = itemService.save(itemForm, imageUrl);

		return ResponseEntity.created(location).body(new ItemApiResult(item));
	}

	@GetMapping(value = "item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ItemApiResult> findItem(@PathVariable Long id) {
		Item item = itemService.findById(id);
		return ResponseEntity.ok(new ItemApiResult(item));
	}

	@GetMapping(value = "item", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ItemApiResult>> findAllItem(Pageable pageable) {
		List<Item> items = itemService.findItems(pageable);
		return ResponseEntity.ok(items.stream().map(ItemApiResult::new).collect(Collectors.toList()));
	}

	@GetMapping(value = "item/recent", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ItemApiResult>> findItem(@RequestParam(value = "offset", defaultValue = "1") int offset,
		@RequestParam(value = "limit", defaultValue = "4") int limit) {
		List<ItemApiResult> apiResults = itemService.findItemsPerCategory(offset, limit)
			.stream()
			.map(ItemApiResult::new)
			.collect(Collectors.toList());
		return ResponseEntity.ok(apiResults);
	}

	@PutMapping("member/{memberId}/item")
	public ResponseEntity<ItemApiResult> editItem(@RequestBody ItemForm itemForm) {
		Item item = itemService.save(itemForm);
		return ResponseEntity.ok(new ItemApiResult(item));
	}

	@PatchMapping("member/{memberId}/item/{id}/status")
	public ResponseEntity<Boolean> requestUpdateItemStatus(@RequestParam Status status, @PathVariable Long id) {
		return ResponseEntity.ok(itemService.updateStatus(id, status));
	}

	@DeleteMapping("member/{memberId}/item")
	public ResponseEntity<Integer> deleteAssignedItems(@RequestParam Status status) {
		return ResponseEntity.ok(itemService.removeStatusDel(status));
	}
}