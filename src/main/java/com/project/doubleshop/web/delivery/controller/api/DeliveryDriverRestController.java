package com.project.doubleshop.web.delivery.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doubleshop.domain.delivery.service.DeliveryDriverService;
import com.project.doubleshop.web.delivery.dto.DeliveryDriverDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/delivery/driver")
@RequiredArgsConstructor
public class DeliveryDriverRestController {

	private final DeliveryDriverService deliveryDriverService;

	@PostMapping
	public ResponseEntity<DeliveryDriverDTO> newDeliveryDriver(@RequestBody DeliveryDriverForm deliveryDriverForm) {

	}
}
