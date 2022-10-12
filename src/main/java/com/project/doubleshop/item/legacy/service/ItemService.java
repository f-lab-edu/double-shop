package com.project.doubleshop.item.legacy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.category.entity.Category;
import com.project.doubleshop.category.service.CategoryService;
import com.project.doubleshop.common.Status;
import com.project.doubleshop.exception.NotFoundException;
import com.project.doubleshop.item.domain.Item;
import com.project.doubleshop.item.legacy.repository.ItemRepository;
import com.project.doubleshop.item.infrastructure.querydsl.ItemQueryApiResult;
import com.project.doubleshop.web.item.dto.ItemForm;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
	private final ItemRepository itemRepository;

	private final CategoryService categoryService;

	@Transactional
	public Item save(ItemForm itemForm) {
		return save(itemForm, null);
	}

	@Transactional
	public Item save(ItemForm itemForm, String imageUrl) {
		Category category = categoryService.findById(itemForm.getCategoryId());
		Item item = Item.convertToItem(itemForm);
		item.setCategory(category);

		if (imageUrl != null) {
			item.setImageUri(imageUrl);
		}

		return itemRepository.save(item);
	}

	public List<Item> findItemsPerCategory(int offset, int limit) {
		List<ItemQueryApiResult> itemsPerCategory = itemRepository.findItemsPerCategory(PageRequest.of(offset, limit));
		return itemsPerCategory.stream().map(queryApiResult -> Item.builder()
			.id(queryApiResult.getId())
			.name(queryApiResult.getName())
			.description(queryApiResult.getDescription())
			.brandName(queryApiResult.getBrandName())
			.price(queryApiResult.getPrice())
			.rating(queryApiResult.getRating())
			.searchKeyword(queryApiResult.getSearchKeyword())
			.category(categoryService.findById(queryApiResult.getCategoryId()))
			.build()).collect(Collectors.toList());
	}

	public Item findById(Long itemId) {
		Item item = itemRepository.findById(itemId)
			.orElseThrow(() -> new NotFoundException(String.format("Item ID '%s' not found.", itemId)));
		Long categoryId = item.getCategory().getId();
		Category category = categoryService.findById(categoryId);
		item.setCategory(category);
		return item;
	}

	public List<Item> findItems(Pageable pageable) {
		List<Item> items = itemRepository.findAllByStatus(Status.ACTIVATED, pageable).getContent();
		for (Item item : items) {
			Long categoryId = item.getCategory().getId();
			item.setCategory(categoryService.findById(categoryId));
		}
		return items;
	}

	@Transactional
	public void remove(List<Long> itemIds) {
		itemRepository.deleteAllById(itemIds);
	}

	@Transactional
	public Boolean updateStatus(Long itemId, Status status) {
		Item item = findById(itemId);
		Status previous = item.getStatus();
		item.saveStatus(status);
		return !previous.equals(item.getStatus());
	}

	@Transactional
	public Integer removeStatusDel(Status status) {
		List<Long> ids = itemRepository.findIdsByStatus(status.getValue());
		itemRepository.deleteAllById(ids);
		return ids.size();
	}
}
