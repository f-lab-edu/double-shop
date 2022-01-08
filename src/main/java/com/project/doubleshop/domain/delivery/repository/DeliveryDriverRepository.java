package com.project.doubleshop.domain.delivery.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.doubleshop.domain.common.Manageable;
import com.project.doubleshop.domain.delivery.entity.DeliveryDriver;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.config.support.Pageable;

@Repository
public interface DeliveryDriverRepository extends Manageable<StatusRequest> {

	boolean save(DeliveryDriver deliveryDriver);

	DeliveryDriver findById(Long id);

	List<DeliveryDriver> findAll(Pageable pageable);

	List<DeliveryDriver> findValidDrivers();
}
