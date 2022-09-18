package com.project.doubleshop.delivery.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.common.Status;
import com.project.doubleshop.delivery.entity.Delivery;
import com.project.doubleshop.delivery.repository.DeliveryPolicyRepository;
import com.project.doubleshop.delivery.repository.DeliveryRepository;
import com.project.doubleshop.exception.NotFoundException;
import com.project.doubleshop.web.delivery.dto.DeliveryApiResult;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeliveryService {

	private final DeliveryRepository deliveryRepository;

	private final DeliveryPolicyRepository deliveryPolicyRepository;

	@Transactional
	public Delivery save(Delivery delivery) {
		return deliveryRepository.save(delivery);
	}

	public Delivery updateDelivery(Delivery submit, Long deliveryId) {
		Delivery previous = findById(deliveryId);
		if (previous != null && previous.getId().equals(submit.getId())) {
			return save(submit);
		}
		if (previous == null) {
			throw new NotFoundException(String.format("Delivery ID '%s' not found.", deliveryId));
		} else {
			throw new IllegalArgumentException(String.format("Something wrong with delivery ID %d.", deliveryId));
		}
	}

	public Delivery findById(Long deliveryId) {
		return deliveryRepository.findById(deliveryId)
			.orElseThrow(() -> new NotFoundException(String.format("Delivery ID '%s' not found.", deliveryId)));
	}

	public DeliveryApiResult findDeliveryApiResultByDeliveryId(Long deliveryId) {
		return deliveryRepository.findDeliveryAndPolicyByDeliveryId(deliveryId);
	}

	public List<Delivery> findDeliveries(Pageable pageable) {
		return deliveryRepository.findAll(pageable).getContent();
	}

	@Transactional
	public Boolean updateStatus(Long deliveryId, Status status) {
		Delivery delivery = findById(deliveryId);
		Status previous = delivery.getStatus();
		delivery.saveStatus(status);
		return !previous.equals(delivery.getStatus());
	}

	@Transactional
	public Integer removeStatusDel(Status status) {
		List<Long> ids = deliveryRepository.findIdsByStatus(status.getValue());
		deliveryRepository.deleteAllById(ids);
		return ids.size();
	}
}
