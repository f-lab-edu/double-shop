package com.project.doubleshop.domain.item.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.mapper.ItemMapper;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.item.dto.ItemStatusRequest;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisItemRepository implements ItemRepository {

	private final ItemMapper mapper;

	@Override
	public boolean save(Item item) {
		int affectedRowNum;
		if(item.getId() != null) {
			affectedRowNum = mapper.updateItem(item);
		} else {
			affectedRowNum = mapper.insertItem(item);
		}
		return affectedRowNum != 0;
	}

	@Override
	public Item findById(Long id) {
		return mapper.selectById(id);
	}

	@Override
	public List<Item> findAll(Pageable pageable) {
		return mapper.selectAllItems(pageable);
	}

	@Override
	public int assignStatus(ItemStatusRequest requestDto) {
		return mapper.assignItemStatus(requestDto);
	}

	@Override
	public int deleteAssignedData(Status status) {
		return mapper.deleteItem(status);
	}
}
