package com.project.doubleshop.delivery.repository.legacy;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.doubleshop.common.Manageable;
import com.project.doubleshop.delivery.entity.DeliveryPolicy;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.config.support.Pageable;

@Repository
public interface DeliveryPolicyRepository extends Manageable<StatusRequest> {

	boolean save(DeliveryPolicy deliveryPolicy);

	DeliveryPolicy findById(Long id);

	List<DeliveryPolicy> findAll(Pageable pageable);
}
