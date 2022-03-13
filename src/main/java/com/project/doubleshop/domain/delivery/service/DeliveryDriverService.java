package com.project.doubleshop.domain.delivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.delivery.entity.legacy.DeliveryDriver;
import com.project.doubleshop.domain.delivery.repository.DeliveryDriverRepository;
import com.project.doubleshop.domain.exception.NotFoundException;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.config.support.Pageable;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeliveryDriverService {

	private final DeliveryDriverRepository deliveryDriverRepository;

	@Transactional
	public boolean saveDeliveryDriver(DeliveryDriver deliveryDriver) {
		return deliveryDriverRepository.save(deliveryDriver);
	}

	@Transactional
	public DeliveryDriver saveDeliveryDriver(DeliveryDriver deliveryDriver, Long deliveryDriverId) {
		findByDeliverDriverId(deliveryDriverId).orElseThrow(() ->
			new NotFoundException(String.format("DeliveryDriver ID '%s' not found.", deliveryDriverId)));
		deliveryDriverRepository.save(deliveryDriver);
		return deliveryDriverRepository.findById(deliveryDriverId);
	}

	public Optional<DeliveryDriver> findByDeliverDriverId(Long deliveryDriverId) {
		return Optional.ofNullable(deliveryDriverRepository.findById(deliveryDriverId));
	}

	public List<DeliveryDriver> findDeliverDrivers(Pageable pageable) {
		return deliveryDriverRepository.findAll(pageable);
	}

	public DeliveryDriver findById(Long deliveryDriverId) {
		return findByDeliverDriverId(deliveryDriverId).orElseThrow(() ->
			new NotFoundException(String.format("DeliveryDriver ID '%s' not found.", deliveryDriverId)));
	}

	@Transactional
	public DeliveryDriver getInsertedDeliveryDriver(DeliveryDriver deliveryDriver) {
		if (saveDeliveryDriver(deliveryDriver)) {
			return deliveryDriver;
		} else {
			throw new NotFoundException(String.format("Inserted deliveryPolicy id %d not found", deliveryDriver.getId()));
		}
	}

	@Transactional
	public void updateDeliveryDriverStatus(StatusRequest requestDTO) {
		DeliveryDriver deliveryDriver = deliveryDriverRepository.findById(requestDTO.getId());
		if (deliveryDriver == null) {
			throw new NotFoundException(String.format("DeliveryDriver Id '%s' not found.", requestDTO.getId()));
		}
		if (Status.of(requestDTO.getStatus().name()) == null) {
			throw new NotFoundException(String.format("Request status value '%s' not found", requestDTO.getStatus().name()));
		}
		deliveryDriverRepository.updateStatus(requestDTO);
	}

	@Transactional
	public DeliveryDriver updateDeliveryDriverStatus(Status status, Long deliveryDriverId) {
		updateDeliveryDriverStatus(new StatusRequest(deliveryDriverId, status));
		return findById(deliveryDriverId);
	}

	@Transactional
	public Integer deleteDeliveryDrivers(Status status) {
		return deliveryDriverRepository.deleteData(status);
	}
}
