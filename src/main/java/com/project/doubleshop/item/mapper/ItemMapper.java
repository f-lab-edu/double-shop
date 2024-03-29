package com.project.doubleshop.item.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.doubleshop.common.Status;
import com.project.doubleshop.common.mapper.param.RequestItemsWithCategory;
import com.project.doubleshop.item.entity.Item;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.item.dto.ItemStockQuery;

@Mapper
public interface ItemMapper {
    int insertItem(Item item);

    Item selectByItemId(Long id);

    List<Item> selectAllItems(Pageable pageable);

    List<Item> selectItemsWithCategory(RequestItemsWithCategory request);

    int updateItem(Item item);

    int updateItemStatus(StatusRequest statusRequest);

    int deleteItem(Status status);

    List<Item> selectItemsInIds(List<Long> itemIds);

    int batchUpdateItems(List<ItemStockQuery> queryList);
}