package com.project.doubleshop.domain.item.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.doubleshop.domain.item.entity.Item;

@Mapper
public interface ItemMapper {
    int insertItem(Item item);
    Item selectById(Long id);
    List<Item> selectAllItems();
    int updateItem(Item item);
    int deleteItem(Long id);
}