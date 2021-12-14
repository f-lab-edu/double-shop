package com.project.doubleshop.domain.delivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.delivery.entity.DeliveryPolicy;
import com.project.doubleshop.domain.delivery.repository.DeliveryPolicyRepository;
import com.project.doubleshop.web.common.StatusRequest;
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

	@Transactional
	public DeliveryPolicy getInsertedDeliveryPolicy(DeliveryPolicy deliveryPolicy) {
		if (saveDeliveryPolicy(deliveryPolicy)) {
			return deliveryPolicy;
		} else {
			throw new DataNotFoundException(String.format("Inserted deliveryPolicy id %d not found", deliveryPolicy.getId()));
		}
	}

	@Transactional
	public void updateDeliveryPolicyStatus(StatusRequest requestDTO) {
		DeliveryPolicy deliveryPolicy = deliveryPolicyRepository.findById(requestDTO.getId());
		if (deliveryPolicy == null) {
			throw new DataNotFoundException(String.format("DeliveryPolicy Id '%s' not found.", requestDTO.getId()));
		}
		if (Status.of(requestDTO.getStatus().name()) == null) {
			throw new IllegalArgumentException(String.format("Request status value '%s' not found", requestDTO.getStatus().name()));
		}
		deliveryPolicyRepository.updateStatus(requestDTO);
	}

	@Transactional
	public DeliveryPolicy updateDeliveryPolicyStatus(Status status, Long deliveryPolicyId) {
		updateDeliveryPolicyStatus(new StatusRequest(deliveryPolicyId, status));
		return findById(deliveryPolicyId);
	}

	@Transactional
	public Integer deleteDeliveryPolices(Status status) {
		return deliveryPolicyRepository.deleteData(status);
	}
}
