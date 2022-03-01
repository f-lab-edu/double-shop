package com.project.doubleshop.domain.category.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.doubleshop.domain.category.entity.Category;
import com.project.doubleshop.domain.common.Status;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	@Query(value = "select c.id from category c where c.status = ?", nativeQuery = true)
	List<Long> findIdsByStatus(Status status);
}
