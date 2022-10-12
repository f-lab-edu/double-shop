package com.project.doubleshop.item.domain;

import java.util.Optional;


public interface ItemRepository {
	Item save(Item item);
	Optional<Item> findById(Long id);
}
