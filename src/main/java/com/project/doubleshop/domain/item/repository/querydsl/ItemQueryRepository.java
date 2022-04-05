package com.project.doubleshop.domain.item.repository.querydsl;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.web.item.dto.ItemApiResult;
import com.querydsl.core.Tuple;

public interface ItemQueryRepository {
	List<ItemQueryApiResult> findItemsPerCategory(Pageable pageable);
}
