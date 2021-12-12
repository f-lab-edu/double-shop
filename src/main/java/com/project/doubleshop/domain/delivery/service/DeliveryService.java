package com.project.doubleshop.domain.delivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.delivery.entity.Delivery;
import com.project.doubleshop.domain.delivery.repository.DeliveryRepository;
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
			new DataNotFoundException(String.format("Delivery ID '%s' not found.", delivery)));
		deliveryRepository.save(delivery);
		return deliveryRepository.findById(deliveryId);
	}

	public Optional<Delivery> findByDeliveryId(Long deliveryId) {
		return Optional.ofNullable(deliveryRepository.findById(deliveryId));
	}

	public List<Delivery> findDeliveries(Pageable pageable) {
		return deliveryRepository.findAll(pageable);
	}
}
