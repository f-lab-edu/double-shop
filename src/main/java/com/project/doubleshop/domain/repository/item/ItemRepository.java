package com.project.doubleshop.domain.repository.item;

import java.util.List;

import com.project.doubleshop.domain.entity.item.Item;

public interface ItemRepository {
	boolean save(Item entity);
	Item findById(Long id);
	List<Item> findAll();
}
