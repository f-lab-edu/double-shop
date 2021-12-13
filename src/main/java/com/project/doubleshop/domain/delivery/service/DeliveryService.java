package com.project.doubleshop.domain.delivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.delivery.entity.Delivery;
import com.project.doubleshop.domain.delivery.repository.DeliveryRepository;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.item.exception.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeliveryService {

	private final DeliveryRepository deliveryRepository;

	@Transactional
	public boolean saveDelivery(Delivery delivery) {
		return deliveryRepository.save(delivery);
	}

	@Transactional
	public Delivery saveDelivery(Delivery delivery, Long deliveryId) {
		findByDeliveryId(deliveryId).orElseThrow(() ->
			new DataNotFoundException(String.format("Delivery ID '%s' not found.", deliveryId)));
		deliveryRepository.save(delivery);
		return deliveryRepository.findById(deliveryId);
	}

	public Optional<Delivery> findByDeliveryId(Long deliveryId) {
		return Optional.ofNullable(deliveryRepository.findById(deliveryId));
	}

	public Delivery findById(Long deliveryId) {
		return findByDeliveryId(deliveryId).orElseThrow(() -> new DataNotFoundException(String.format("Delivery ID '%s' not found.", deliveryId)));
	}

	public List<Delivery> findDeliveries(Pageable pageable) {
		return deliveryRepository.findAll(pageable);
	}

	@Transactional
	public Delivery getInsertedDelivery(Delivery delivery) {
		if (saveDelivery(delivery)) {
			return delivery;
		} else {
			throw new DataNotFoundException(String.format("Inserted delivery id %d not found", delivery.getId()));
		}
	}

	@Transactional
	public void updateDeliveryStatus(StatusRequest requestDTO) {
		Delivery delivery = deliveryRepository.findById(requestDTO.getId());
		if (delivery == null) {
			throw new DataNotFoundException(String.format("Delivery Id '%s' not found.", requestDTO.getId()));
		}
		if (Status.of(requestDTO.getStatus().name()) == null) {
			throw new IllegalArgumentException(String.format("Request status value '%s' not found", requestDTO.getStatus().name()));
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
