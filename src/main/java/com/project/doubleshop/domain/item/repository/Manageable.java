package com.project.doubleshop.domain.item.repository;

import com.project.doubleshop.domain.common.Status;

public interface Manageable<T> {
	int assignStatus(T requestDTO);
	int deleteAssignedData(Status status);
}
