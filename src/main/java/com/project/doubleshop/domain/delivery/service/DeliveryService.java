package com.project.doubleshop.domain.delivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.delivery.entity.Delivery;
import com.project.doubleshop.domain.delivery.entity.legacy.DeliveryDriver;
import com.project.doubleshop.domain.delivery.entity.DeliveryPolicy;
import com.project.doubleshop.domain.delivery.repository.DeliveryRepository;
import com.project.doubleshop.domain.exception.NotFoundException;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.delivery.dto.DeliveryApiResult;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeliveryService {

	private final DeliveryRepository deliveryRepository;

	private final DeliveryPolicyService deliveryPolicyService;

	private final DeliveryDriverService deliveryDriverService;

	@Transactional
	public boolean saveDelivery(Delivery delivery) {
		return deliveryRepository.save(delivery);
	}

	@Transactional
	public Delivery saveDelivery(Delivery delivery, Long deliveryId) {
		findByDeliveryId(deliveryId).orElseThrow(() ->
			new NotFoundException(String.format("Delivery ID '%s' not found.", deliveryId)));
		deliveryRepository.save(delivery);
		return deliveryRepository.findById(deliveryId);
	}

	public DeliveryApiResult findDeliveryApiResultByDeliveryId(Long deliveryId) {
		Delivery delivery = findById(deliveryId);

		Long deliveryDriverId = 1L;
		Long deliveryPolicyId = 1L;

		DeliveryPolicy deliveryPolicy = deliveryPolicyService.findById(deliveryPolicyId);
		DeliveryDriver deliveryDriver = deliveryDriverService.findById(deliveryDriverId);

		 return new DeliveryApiResult(delivery, deliveryPolicy, deliveryDriver);
	}

	public Optional<Delivery> findByDeliveryId(Long deliveryId) {
		return Optional.ofNullable(deliveryRepository.findById(deliveryId));
	}

	public Delivery findById(Long deliveryId) {
		return findByDeliveryId(deliveryId).orElseThrow(() -> new NotFoundException(String.format("Delivery ID '%s' not found.", deliveryId)));
	}

	public List<Delivery> findDeliveries(Pageable pageable) {
		return deliveryRepository.findAll(pageable);
	}

	@Transactional
	public Delivery getInsertedDelivery(Delivery delivery) {
		if (saveDelivery(delivery)) {
			return delivery;
		} else {
			throw new NotFoundException(String.format("Inserted delivery id %d not found", delivery.getId()));
		}
	}

	@Transactional
	public void updateDeliveryStatus(StatusRequest requestDTO) {
		Delivery delivery = deliveryRepository.findById(requestDTO.getId());
		if (delivery == null) {
			throw new NotFoundException(String.format("Delivery Id '%s' not found.", requestDTO.getId()));
		}
		if (Status.of(requestDTO.getStatus().name()) == null) {
			throw new NotFoundException(String.format("Request status value '%s' not found", requestDTO.getStatus().name()));
		}
		deliveryRepository.updateStatus(requestDTO);
	}

	@Transactional
	public Delivery updateDeliveryStatus(Status status, Long deliveryId) {
		updateDeliveryStatus(new StatusRequest(deliveryId, status));
		return findById(deliveryId);
	}

	@Transactional
	public Integer deleteDeliveries(Status status) {
		return deliveryRepository.deleteData(status);
	}
}
