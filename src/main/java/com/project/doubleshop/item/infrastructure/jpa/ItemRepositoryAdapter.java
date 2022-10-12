package com.project.doubleshop.item.infrastructure.jpa;

import java.util.Optional;

import com.project.doubleshop.item.domain.Item;
import com.project.doubleshop.item.domain.ItemRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemRepositoryAdapter implements ItemRepository {
	private final JpaItemRepository jpaItemRepository;

	@Override
	public Item save(Item item) {
		return jpaItemRepository.save(item);
	}

	@Override
	public Optional<Item> findById(Long id) {
		return jpaItemRepository.findById(id);
	}
}
