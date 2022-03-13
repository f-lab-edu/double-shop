package com.project.doubleshop.domain.delivery.service.legacy;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeliveryManagementService {
	private final DeliveryService deliveryService;
	
}
