package com.project.doubleshop.domain.item.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.item.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
	@Query(value = "select i.id from Item i where i.status = :statusCode")
	List<Long> findIdsByStatus(Integer statusCode);

	Page<Item> findAllByStatus(Status status, Pageable pageable);
}
