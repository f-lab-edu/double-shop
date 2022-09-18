package com.project.doubleshop.delivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.doubleshop.delivery.entity.DeliveryPolicy;

public interface DeliveryPolicyRepository extends JpaRepository<DeliveryPolicy, Long> {
	@Query("select dp.id from DeliveryPolicy dp where dp.status = :statusCode")
	List<Long> findIdsByStatus(int statusCode);
}
