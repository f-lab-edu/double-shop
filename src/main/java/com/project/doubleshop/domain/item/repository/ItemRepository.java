package com.project.doubleshop.domain.item.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.item.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
	@Query(value = "select i.id from item i where i.status = ?", nativeQuery = true)
	List<Long> findIdsByStatus(Status status);
}
