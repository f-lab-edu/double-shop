package com.project.doubleshop.domain.delivery.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.doubleshop.domain.common.Manageable;
import com.project.doubleshop.domain.delivery.entity.DeliveryPolicy;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.config.support.Pageable;

@Repository
public interface DeliveryPolicyRepository extends Manageable<StatusRequest> {

	boolean save(DeliveryPolicy deliveryPolicy);

	DeliveryPolicy findById(Long id);

	List<DeliveryPolicy> findAll(Pageable pageable);
}
