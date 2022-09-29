package com.project.doubleshop.item.domain;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.project.doubleshop.common.Status;

public interface ItemRepository {
	Optional<Item> findItemByIdAndStatus(Long id, Status status);

	List<Long> findIdsByStatus(Integer statusCode);

	void deleteAllByIdsAndStatusCode(Collection<Long> ids, Integer statusCode);
}
