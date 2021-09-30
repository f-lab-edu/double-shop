package com.project.doubleshop.domain.item.repository;

import java.util.List;

import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.web.item.dto.ItemStatusRequest;

public interface ItemRepository extends Manageable<ItemStatusRequest> {
	boolean save(Item entity);
	Item findById(Long id);
	List<Item> findAll();
}
