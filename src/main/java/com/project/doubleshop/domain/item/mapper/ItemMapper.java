package com.project.doubleshop.domain.item.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.web.item.dto.ItemStatusRequest;

@Mapper
public interface ItemMapper {
    int insertItem(Item item);
    Item selectById(Long id);
    List<Item> selectAllItems();
    int updateItem(Item item);
    int assignItemStatus(ItemStatusRequest itemStatusRequest);
    int deleteItem(Status status);
}