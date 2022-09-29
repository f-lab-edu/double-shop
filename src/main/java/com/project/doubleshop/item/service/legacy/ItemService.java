package com.project.doubleshop.item.service.legacy;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.category.service.legacy.CategoryService;
import com.project.doubleshop.common.Status;
import com.project.doubleshop.common.mapper.param.RequestItemsWithCategory;
import com.project.doubleshop.exception.NotFoundException;
import com.project.doubleshop.item.domain.Item;
import com.project.doubleshop.item.repository.legacy.ItemRepository;
import com.project.doubleshop.utils.ExceptionUtils;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.item.dto.ItemStockQuery;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
	private final ItemRepository itemRepository;

	private final CategoryService categoryService;

	@Transactional
	public boolean saveItem(Item item) {
		return itemRepository.save(item);
	}

	@Transactional
	public Item saveItem(Item item, Long itemId) {
		findItemById(itemId).orElseThrow(() -> new NotFoundException(String.format("Item ID '%s' not found.", itemId)));
		itemRepository.save(item);
		return itemRepository.findById(itemId);
	}

	@Transactional
	public Item saveItemWithCategory(Item item) {
		Long categoryId = item.getCategory().getId();
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
			throw new NotFoundException(String.format("Item ID '%s' not found.", itemId));
		}
		Long categoryId = item.getCategory().getId();
		if (categoryId == null) {
			throw new NotFoundException(String.format("Category ID '%s' not found.", categoryId));
		}
		return item;
	}

	public List<Item> findItems(Pageable pageable) {
		return itemRepository.findAll(pageable);
	}

	public List<Item> findItemsWithCategory(RequestItemsWithCategory request) {
		return itemRepository.findAllWithCategory(request);
	}

	public List<Item> findItemsInItemIds(List<Long> itemIds) {
		List<Item> items = itemRepository.findItemsInIds(itemIds);

		if (itemIds.size() != items.size()) {
			Set<Long> validIds = items
				.stream()
				.map(Item::getId)
				.collect(Collectors.toSet());

			List<Long> invalidIds = itemIds
				.stream()
				.filter(id -> !validIds.contains(id))
				.collect(Collectors.toList());

			ExceptionUtils.findInvalidIdsAndThrow404Error(invalidIds, "Invalid item id");
		}
		return items;
	}

	@Transactional
	public void updateItemStatus(StatusRequest requestDTO) {
		Item item = itemRepository.findById(requestDTO.getId());
		if (item == null) {
			throw new NotFoundException(String.format("Item ID '%s' not found", requestDTO.getId()));
		}
		if (Status.of(requestDTO.getStatus().name()) == null) {
			throw new NotFoundException(String.format("Request status value '%s' not found", requestDTO.getStatus().name()));
		}
		itemRepository.updateStatus(requestDTO);
	}

	@Transactional
	public Item updateItemStatus(Status status, Long itemId) {
		updateItemStatus(new StatusRequest(itemId, status));
		return findItemById(itemId).orElseThrow(() -> new NotFoundException(String.format("Item ID '%s' not found", itemId)));
	}

	@Transactional
	public Integer deleteItems(Status status) {
		return itemRepository.deleteData(status);
	}

	public void updateItems(List<ItemStockQuery> queryList) {
		itemRepository.updateItems(queryList);
	}
}