package com.project.doubleshop.item.infrastructure.querydsl;

import java.util.List;

import org.springframework.data.domain.Pageable;

public interface ItemQueryRepository {
	List<ItemQueryApiResult> findItemsPerCategory(Pageable pageable);
}
