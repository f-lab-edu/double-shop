package com.project.doubleshop.domain.item.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.category.entity.Category;
import com.project.doubleshop.domain.category.service.CategoryService;
import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.exception.NotFoundException;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.repository.ItemRepository;
import com.project.doubleshop.domain.utils.ExceptionUtils;
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
		Category category = categoryService.findById(itemForm.getCategoryId());
		Item item = Item.convertToItem(itemForm);
		item.setCategory(category);
		return itemRepository.save(item);
	}

	public Item findById(Long itemId) {
		return itemRepository.findById(itemId)
			.orElseThrow(() -> new NotFoundException(String.format("Item ID '%s' not found.", itemId)));
	}

	public List<Item> findItems(Pageable pageable) {
		return itemRepository.findAllByStatus(Status.ACTIVATED, pageable).getContent();
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
