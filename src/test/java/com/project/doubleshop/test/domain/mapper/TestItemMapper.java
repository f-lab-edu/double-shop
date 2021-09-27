package com.project.doubleshop.test.domain.mapper;

import com.project.doubleshop.test.domain.entity.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestItemMapper {
    int insertItem(Item item);
    Item selectById(Long id);
    List<Item> selectAllItems();
    void updateItem(Item item);
    void deleteItem(Long id);
}
