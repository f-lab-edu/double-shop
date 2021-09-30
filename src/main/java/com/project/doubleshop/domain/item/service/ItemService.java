package com.project.doubleshop.domain.item.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.repository.ItemRepository;
import com.project.doubleshop.web.item.dto.ItemStatusRequest;

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

	public List<Item> findItems() {
		return itemRepository.findAll();
	}

	public void AssignItemStatus(ItemStatusRequest requestDTO) {
		itemRepository.assignStatus(requestDTO);
	}

	public void DeleteAssignedItems(Status status) {
		itemRepository.deleteAssignedData(status);
	}
}