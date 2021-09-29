package com.project.doubleshop.domain.item.repository;

import java.util.List;

import com.project.doubleshop.domain.item.entity.Item;

public interface ItemRepository {
	boolean save(Item entity);
	Item findById(Long id);
	List<Item> findAll();
}
