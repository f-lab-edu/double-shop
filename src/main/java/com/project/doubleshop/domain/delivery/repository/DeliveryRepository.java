package com.project.doubleshop.domain.delivery.repository;

import java.util.List;

import com.project.doubleshop.domain.common.Manageable;
import com.project.doubleshop.domain.delivery.entity.Delivery;
import com.project.doubleshop.domain.delivery.entity.DeliveryStatus;
import com.project.doubleshop.domain.delivery.service.DispatchDriver;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.config.support.Pageable;

public interface DeliveryRepository extends Manageable<StatusRequest> {

	boolean save(Delivery delivery);

	Delivery findById(Long id);

	List<Delivery> findAll(Pageable pageable);

	void bulkInsert(List<Delivery> deliveries);

	List<Delivery> findDeliveriesByDeliveryStatus(DeliveryStatus statusPreparation);

	void bulkUpdateDeliveryStatusByOrderIds(List<Long> deliveryIds, DeliveryStatus statusBeginning);

	void batchUpdate(List<DispatchDriver> result);
}
