package com.project.doubleshop.delivery.repository.legacy;

import java.util.List;

import com.project.doubleshop.common.Manageable;
import com.project.doubleshop.delivery.entity.Delivery;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.config.support.Pageable;

public interface DeliveryRepository extends Manageable<StatusRequest> {

	boolean save(Delivery delivery);

	Delivery findById(Long id);

	List<Delivery> findAll(Pageable pageable);
}
