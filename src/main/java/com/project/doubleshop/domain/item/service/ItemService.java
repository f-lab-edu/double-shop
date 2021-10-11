package com.project.doubleshop.domain.item.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.category.service.CategoryService;
import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.common.mapper.param.RequestItemsWithCategory;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.repository.ItemRepository;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.item.dto.ItemApiResult;
import com.project.doubleshop.web.item.exception.InvalidArgumentException;
import com.project.doubleshop.web.item.exception.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
	private final ItemRepository itemRepository;

	private final CategoryService categoryService;

	private final Validator validator;

	@Transactional
	public boolean saveItem(Item item) {
		// Set<ConstraintViolation<Item>> violations = validator.validate(item);
		// // 테스트용 service layered validation - 실질적으로 사용하지는 않는 validation 추후 개선할 예정.
		// if (!violations.isEmpty()) {
		// 	StringBuilder sb = new StringBuilder();
		// 	for (ConstraintViolation<Item> violation : violations) {
		// 		sb.append(violation.getMessage()).append(" ");
		// 	}
		// 	throw new InvalidArgumentException(sb.toString());
		// }
		return itemRepository.save(item);
	}

	@Transactional
	public Item saveItem(Item item, Long itemId) {
		findItemById(itemId).orElseThrow(() -> new DataNotFoundException(String.format("item ID '%s' not found.", itemId)));
		itemRepository.save(item);
		return itemRepository.findById(itemId);
	}

	@Transactional
	public Item saveItemWithCategory(Item item) {
		Long categoryId = item.getCategoryId();
		categoryService.findCategoryById(categoryId);
		saveItem(item);
		return item;
	}

	public Optional<Item> findItemById(Long itemId) {
		return Optional.ofNullable(itemRepository.findById(itemId));
	}

	public Item findItemByIdWithCategory(Long itemId) {
		Item item = itemRepository.findById(itemId);
		if (item == null) {
			throw new DataNotFoundException(String.format("item ID '%s' not found.", itemId));
		}
		Long categoryId = item.getCategoryId();
		if (categoryId == null) {
			throw new DataNotFoundException(String.format("category ID '%s' not found.", categoryId));
		}
		return item;
	}

	public List<Item> findItems(Pageable pageable) {
		return itemRepository.findAll(pageable);
	}

	public List<Item> findItemsWithCategory(RequestItemsWithCategory request) {
		return itemRepository.findAllWithCategory(request);
	}

	@Transactional
	public void updateItemStatus(StatusRequest requestDTO) {
		Item item = itemRepository.findById(requestDTO.getId());
		if (item == null) {
			throw new DataNotFoundException(String.format("item ID '%s' not found", requestDTO.getId()));
		}
		if (Status.of(requestDTO.getStatus().name()) == null) {
			throw new IllegalArgumentException(String.format("request status value '%s' not found", requestDTO.getStatus().name()));
		}
		itemRepository.updateStatus(requestDTO);
	}

	@Transactional
	public Item updateItemStatus(Status status, Long itemId) {
		updateItemStatus(new StatusRequest(itemId, status));
		return findItemById(itemId).orElseThrow(() -> new DataNotFoundException(String.format("item ID '%s' not found", itemId)));
	}

	@Transactional
	public Integer deleteItems(Status status) {
		return itemRepository.deleteData(status);
	}
}