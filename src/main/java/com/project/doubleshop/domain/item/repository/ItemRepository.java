package com.project.doubleshop.domain.item.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.repository.querydsl.ItemQueryRepository;
import com.project.doubleshop.web.item.dto.ItemApiResult;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemQueryRepository {

	@Query(value = "select i.id, i.name, i.category.id from Item i where i.id = :itemId and i.status = :status")
	Optional<Item> findItemByIdAndStatus(Long itemId, Status status);

	@Query(value = "select i.id from Item i where i.status = :statusCode")
	List<Long> findIdsByStatus(Integer statusCode);

	@Query(value = "select i from Item i where i.status = :status")
	Page<Item> findAllByStatus(Status status, Pageable pageable);

	// @Query(value = "select * from ("
	// 	+ "i.price, i.volume, i.dimension, i.package_type,"
	// 	+ "i.origin, i.expiration, i.allergic_info, i.model_serial_no,"
	// 	+ "i.rating, i.search_keyword, i.stock, i.discount_price,"
	// 	+ "i.author, i.publisher, i.isbn, i.image_uri,"
	// 	+ "i.published_time, i.is_oneday_eligible, i.is_fresh_eligible,"
	// 	+ "i.status, i.status_update_time, i.create_time, i.category_id, i.price_per100g,"
	// 	+ "ROW_NUMBER() OVER(partition by category_id order by id desc) as rn from item i"
	// 	+ ") i where rn <= :limit", nativeQuery = true)
	// List<ItemApiResult> findItemsPerCategory(int limit);
}
