package com.project.doubleshop.domain.item.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
	private final ItemRepository itemRepository;

	@Transactional
	public boolean saveItem(Item item) {
		return itemRepository.save(item);
	}

	public Optional<Item> findItem(Long itemId) {
		return Optional.ofNullable(itemRepository.findById(itemId));
	}

	public Optional<List<Item>> findItems() {
		return Optional.ofNullable(itemRepository.findAll());
	}
}