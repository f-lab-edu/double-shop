package com.project.doubleshop.item.infrastructure.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.doubleshop.common.Status;
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

	@Override
	public Optional<Item> findByIdAndStatus(Long id, Status status) {
		return jpaItemRepository.findItemByIdAndStatus(id, status);
	}

	@Override
	public List<Item> findPerPage(Pageable pageable) {
		return jpaItemRepository.findAll(pageable).getContent();
	}

}
