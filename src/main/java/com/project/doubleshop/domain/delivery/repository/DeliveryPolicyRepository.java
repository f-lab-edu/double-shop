package com.project.doubleshop.domain.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.doubleshop.domain.delivery.entity.DeliveryPolicy;

public interface DeliveryPolicyRepository extends JpaRepository<DeliveryPolicy, Long> {
}
