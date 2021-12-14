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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.delivery.entity.Delivery;
import com.project.doubleshop.domain.delivery.entity.DeliveryDriver;
import com.project.doubleshop.domain.delivery.entity.DeliveryPolicy;
import com.project.doubleshop.domain.delivery.service.DeliveryDriverService;
import com.project.doubleshop.domain.delivery.service.DeliveryPolicyService;
import com.project.doubleshop.domain.delivery.service.DeliveryService;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.delivery.dto.DeliveryApiResult;
import com.project.doubleshop.web.delivery.dto.DeliveryDTO;
import com.project.doubleshop.web.delivery.dto.DeliveryForm;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/delivery")
@RequiredArgsConstructor
public class DeliveryRestController {

	private final DeliveryService deliveryService;

	private final DeliveryDriverService deliveryDriverService;

	private final DeliveryPolicyService deliveryPolicyService;

	@PostMapping
	public ResponseEntity<DeliveryDTO> newDelivery(@RequestBody DeliveryForm deliveryForm) {
		Delivery delivery = deliveryService.getInsertedDelivery(Delivery.convertToDelivery(deliveryForm));

		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.build()
			.toUri();

		return ResponseEntity.created(location).body(new DeliveryDTO(delivery));
	}

	@GetMapping("{id}")
	public ResponseEntity<DeliveryApiResult> findDeliveryById(@PathVariable Long id) {
		Delivery delivery = deliveryService.findById(id);

		Long deliveryDriverId = delivery.getDeliveryDriverId();
		Long deliveryPolicyId = delivery.getDeliveryPolicyId();

		DeliveryPolicy deliveryPolicy = deliveryPolicyService.findById(deliveryPolicyId);
		DeliveryDriver deliveryDriver = deliveryDriverService.findById(deliveryDriverId);

		return ResponseEntity.ok(new DeliveryApiResult(delivery, deliveryPolicy, deliveryDriver));
	}

	@GetMapping
	public ResponseEntity<List<DeliveryDTO>> findDeliveries(Pageable pageable) {
		List<Delivery> deliveries = deliveryService.findDeliveries(pageable);
		return ResponseEntity.ok(deliveries.stream()
			.map(DeliveryDTO::new).collect(Collectors.toList()));
	}

	@PutMapping("{id}")
	public ResponseEntity<DeliveryDTO> editDelivery(@RequestBody DeliveryForm deliveryForm, @PathVariable Long id) {
		Delivery delivery = deliveryService.saveDelivery(Delivery.convertToDelivery(deliveryForm), id);
		return ResponseEntity.ok(new DeliveryDTO(delivery));
	}

	@PatchMapping("{id}/status")
	public ResponseEntity<DeliveryDTO>  requestUpdateDeliveryStatus(@RequestBody Status status, @PathVariable Long id) {
		return ResponseEntity.ok(new DeliveryDTO(deliveryService.updateDeliveryStatus(status, id)));
	}

	@DeleteMapping
	public ResponseEntity<Integer> deleteAssignedDeliveries(@RequestParam Status status) {
		return ResponseEntity.ok(deliveryService.deleteDeliveries(status));
	}
}