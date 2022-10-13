package com.project.doubleshop.item.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.project.doubleshop.common.Status;


public interface ItemRepository {
	Item save(Item item);
	Optional<Item> findById(Long id);
	Optional<Item> findByIdAndStatus(Long id, Status status);
	List<Item> findPerPage(Pageable pageable);
}
