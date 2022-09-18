package com.project.doubleshop.delivery.repository.legacy;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.doubleshop.common.Manageable;
import com.project.doubleshop.delivery.entity.legacy.DeliveryDriver;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.config.support.Pageable;

@Repository
public interface DeliveryDriverRepository extends Manageable<StatusRequest> {

	boolean save(DeliveryDriver deliveryDriver);

	DeliveryDriver findById(Long id);

	List<DeliveryDriver> findAll(Pageable pageable);
}
