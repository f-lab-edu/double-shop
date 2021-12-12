package com.project.doubleshop.domain.delivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.delivery.entity.DeliveryDriver;
import com.project.doubleshop.domain.delivery.repository.DeliveryDriverRepository;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.item.exception.DataNotFoundException;

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
			new DataNotFoundException(String.format("DeliveryDriver ID '%s' not found.", deliveryDriverId)));
		deliveryDriverRepository.save(deliveryDriver);
		return deliveryDriverRepository.findById(deliveryDriverId);
	}

	public Optional<DeliveryDriver> findByDeliverDriverId(Long deliveryDriverId) {
		return Optional.ofNullable(deliveryDriverRepository.findById(deliveryDriverId));
	}

	public List<DeliveryDriver> findDeliverDrivers(Pageable pageable) {
		return deliveryDriverRepository.findAll(pageable);
	}
}
