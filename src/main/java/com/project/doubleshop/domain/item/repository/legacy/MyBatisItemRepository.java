package com.project.doubleshop.domain.item.repository.legacy;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.common.mapper.param.RequestItemsWithCategory;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.mapper.ItemMapper;
import com.project.doubleshop.domain.item.repository.legacy.ItemRepository;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.item.dto.ItemStockQuery;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisItemRepository implements ItemRepository {

	private final ItemMapper mapper;

	@Override
	public boolean save(Item item) {
		int affectedRowCnt;
		if (item.getId() != null) {
			affectedRowCnt = mapper.updateItem(item);
		} else {
			affectedRowCnt = mapper.insertItem(item);
		}
		return affectedRowCnt != 0;
	}

	@Override
	public Item findById(Long id) {
		return mapper.selectByItemId(id);
	}

	@Override
	public List<Item> findAll(Pageable pageable) {
		return mapper.selectAllItems(pageable);
	}

	@Override
	public List<Item> findAllWithCategory(RequestItemsWithCategory request) {
		return mapper.selectItemsWithCategory(request);
	}

	@Override
	public List<Item> findItemsInIds(List<Long> itemIds) {
		return mapper.selectItemsInIds(itemIds);
	}

	@Override
	public int updateItems(List<ItemStockQuery> queryList) {
		return mapper.batchUpdateItems(queryList);
	}

	@Override
	public int updateStatus(StatusRequest requestDto) {
		return mapper.updateItemStatus(requestDto);
	}

	@Override
	public int deleteData(Status status) {
		return mapper.deleteItem(status);
	}
}
