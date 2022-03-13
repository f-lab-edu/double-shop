package com.project.doubleshop.web.delivery.controller.api;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.delivery.entity.legacy.DeliveryDriver;
import com.project.doubleshop.domain.delivery.service.DeliveryDriverService;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.delivery.dto.DeliveryDriverDTO;
import com.project.doubleshop.web.delivery.dto.DeliveryDriverForm;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/delivery/driver")
@RequiredArgsConstructor
public class DeliveryDriverRestController {

	private final DeliveryDriverService deliveryDriverService;

	@PostMapping
	public ResponseEntity<DeliveryDriverDTO> newDeliveryDriver(@RequestBody DeliveryDriverForm deliveryDriverForm) {
		DeliveryDriver deliveryDriver =
			deliveryDriverService.getInsertedDeliveryDriver(DeliveryDriver.convertToDeliveryDriver(deliveryDriverForm));

		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.build()
			.toUri();

		return ResponseEntity.created(location).body(new DeliveryDriverDTO(deliveryDriver));
	}

	@GetMapping("{id}")
	public ResponseEntity<DeliveryDriverDTO> findDeliveryDriverById(@PathVariable Long id) {
		DeliveryDriver deliveryDriver = deliveryDriverService.findById(id);

		return ResponseEntity.ok(new DeliveryDriverDTO(deliveryDriver));
	}

	@GetMapping
	public ResponseEntity<List<DeliveryDriverDTO>> findDeliveryPolicies(Pageable pageable) {
		List<DeliveryDriver> deliverDrivers = deliveryDriverService.findDeliverDrivers(pageable);
		return ResponseEntity.ok(deliverDrivers.stream().map(DeliveryDriverDTO::new).collect(Collectors.toList()));
	}

	@PutMapping("{id}")
	public ResponseEntity<DeliveryDriverDTO> editDeliveryDriver(@RequestBody DeliveryDriverForm deliveryDriverForm,
		@PathVariable Long id) {
		DeliveryDriver deliveryDriver = deliveryDriverService.saveDeliveryDriver(
			DeliveryDriver.convertToDeliveryDriver(deliveryDriverForm), id);
		return ResponseEntity.ok(new DeliveryDriverDTO(deliveryDriver));
	}

	@PatchMapping("{id}/status")
	public ResponseEntity<DeliveryDriverDTO> requestUpdateDeliveryDriverStatus(@RequestBody Status status, @PathVariable Long id) {
		return ResponseEntity.ok(new DeliveryDriverDTO(deliveryDriverService.updateDeliveryDriverStatus(status, id)));
	}

	@DeleteMapping
	public ResponseEntity<Integer> deleteAssignedDeliveryDrivers(@RequestBody Status status) {
		return ResponseEntity.ok(deliveryDriverService.deleteDeliveryDrivers(status));
	}
}
