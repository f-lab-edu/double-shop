package com.project.doubleshop.item.infrastructure.jpa;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.doubleshop.common.Status;
import com.project.doubleshop.item.domain.Item;

public interface JpaItemRepository extends JpaRepository<Item, Long> {
	Optional<Item> findItemByIdAndStatus(Long id, Status status);
}
