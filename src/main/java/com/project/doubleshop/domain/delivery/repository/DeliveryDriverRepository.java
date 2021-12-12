package com.project.doubleshop.domain.delivery.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.doubleshop.domain.delivery.entity.DeliveryDriver;
import com.project.doubleshop.web.config.support.Pageable;

@Repository
public interface DeliveryDriverRepository {

	boolean save(DeliveryDriver deliveryDriver);

	DeliveryDriver findById(Long id);

	List<DeliveryDriver> findAll(Pageable pageable);
}
