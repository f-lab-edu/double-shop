package com.project.doubleshop.item.infrastructure;

import com.project.doubleshop.common.Status;
import com.project.doubleshop.exception.NotFoundException;
import com.project.doubleshop.item.domain.Item;
import com.project.doubleshop.item.domain.ItemRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemFinder {
	private final ItemRepository itemRepository;

	public Item find(Long itemId) {
		return itemRepository.findByIdAndStatus(itemId, Status.ACTIVATED)
			.orElseThrow(() -> new NotFoundException(String.format("Item ID '%s' not found.", itemId)));
	}
}
