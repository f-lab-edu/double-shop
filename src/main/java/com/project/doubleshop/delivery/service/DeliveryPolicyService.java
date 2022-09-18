package com.project.doubleshop.delivery.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.common.Status;
import com.project.doubleshop.delivery.entity.DeliveryPolicy;
import com.project.doubleshop.delivery.repository.DeliveryPolicyRepository;
import com.project.doubleshop.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeliveryPolicyService {

	private final DeliveryPolicyRepository deliveryPolicyRepository;

	@Transactional
	public DeliveryPolicy save(DeliveryPolicy deliveryPolicy) {
		return deliveryPolicyRepository.save(deliveryPolicy);
	}

	public DeliveryPolicy findById(Long deliveryPolicyId) {
		return deliveryPolicyRepository.findById(deliveryPolicyId)
			.orElseThrow(() -> new NotFoundException(String.format("DeliveryPolicy ID '%s' not found.", deliveryPolicyId)));
	}

	public List<DeliveryPolicy> findDeliveryPolicies(Pageable pageable) {
		return deliveryPolicyRepository.findAll(pageable).getContent();
	}

	public DeliveryPolicy updateDeliveryPolicy(Long deliveryPolicyId, DeliveryPolicy submit) {
		DeliveryPolicy previous = findById(deliveryPolicyId);
		if (previous != null && previous.getId().equals(submit.getId())) {
			return save(submit);
		}
		if (previous == null) {
			throw new NotFoundException(String.format("DeliveryPolicy ID '%s' not found.", deliveryPolicyId));
		} else {
			throw new IllegalArgumentException(String.format("Something wrong with deliveryPolicy ID %d.", deliveryPolicyId));
		}
	}

	@Transactional
	public Boolean updateStatus(Long deliveryPolicyId, Status status) {
		DeliveryPolicy deliveryPolicy = findById(deliveryPolicyId);
		Status previous = deliveryPolicy.getStatus();
		deliveryPolicy.saveStatus(status);
		return !previous.equals(deliveryPolicy.getStatus());
	}

	@Transactional
	public Integer removeStatusDel(Status status) {
		List<Long> ids = deliveryPolicyRepository.findIdsByStatus(status.getValue());
		deliveryPolicyRepository.deleteAllById(ids);
		return ids.size();
	}
}
