package com.project.doubleshop.test.domain.mapper;

import com.project.doubleshop.test.domain.entity.Item;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemMapper {
    int insertItem(Item item);
    Item selectById(Long id);
    void updateItem(Item item);
    void deleteItem(Long id);
}
