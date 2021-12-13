package com.project.doubleshop.domain.delivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.delivery.entity.DeliveryPolicy;
import com.project.doubleshop.domain.delivery.repository.DeliveryPolicyRepository;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.item.exception.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeliveryPolicyService {

	private final DeliveryPolicyRepository deliveryPolicyRepository;

	@Transactional
	public boolean saveDeliveryPolicy(DeliveryPolicy deliveryPolicy) {
		return deliveryPolicyRepository.save(deliveryPolicy);
	}

	@Transactional
	public DeliveryPolicy saveDeliveryPolicy(DeliveryPolicy deliveryPolicy, Long deliveryPolicyId) {
		findByDeliveryPolicyId(deliveryPolicyId).orElseThrow(() ->
			new DataNotFoundException(String.format("DeliveryPolicy ID '%s' not found.", deliveryPolicyId)));
		deliveryPolicyRepository.save(deliveryPolicy);
		return deliveryPolicyRepository.findById(deliveryPolicyId);
	}

	public Optional<DeliveryPolicy> findByDeliveryPolicyId(Long deliveryPolicyId) {
		return Optional.ofNullable(deliveryPolicyRepository.findById(deliveryPolicyId));
	}

	public List<DeliveryPolicy> findDeliveryPolicies(Pageable pageable) {
		return deliveryPolicyRepository.findAll(pageable);
	}

	public DeliveryPolicy findById(Long deliveryPolicyId) {
		return findByDeliveryPolicyId(deliveryPolicyId).orElseThrow(() ->
			new DataNotFoundException(String.format("Delivery ID '%s' not found.", deliveryPolicyId)));
	}
}
