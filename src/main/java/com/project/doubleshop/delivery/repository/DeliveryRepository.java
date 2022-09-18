package com.project.doubleshop.delivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.doubleshop.delivery.entity.Delivery;
import com.project.doubleshop.web.delivery.dto.DeliveryApiResult;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
	@Query(value = "select d from Delivery d"
		+ " join DeliveryPolicy dp on d.deliveryPolicy.id = dp.id"
		+ " where d.id = :deliveryId")
	DeliveryApiResult findDeliveryAndPolicyByDeliveryId(Long deliveryId);

	@Query("select d.id from Delivery d where d.status = :statusCode")
	List<Long> findIdsByStatus(int statusCode);
}
