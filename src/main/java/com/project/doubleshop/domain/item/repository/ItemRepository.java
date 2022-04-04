package com.project.doubleshop.domain.item.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.item.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

	@Query(value = "select i.id, i.name, i.category.id from Item i where i.id = :itemId and i.status = :status")
	Optional<Item> findItemByIdAndStatus(Long itemId, Status status);

	@Query(value = "select i.id from Item i where i.status = :statusCode")
	List<Long> findIdsByStatus(Integer statusCode);

	@Query(value = "select i from Item i where i.status = :status")
	Page<Item> findAllByStatus(Status status, Pageable pageable);
}
