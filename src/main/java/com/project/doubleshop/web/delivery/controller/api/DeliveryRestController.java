package com.project.doubleshop.web.delivery.controller.api;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
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
import com.project.doubleshop.domain.delivery.service.DeliveryService;

import com.project.doubleshop.web.delivery.dto.DeliveryApiResult;
import com.project.doubleshop.web.delivery.dto.DeliveryDTO;
import com.project.doubleshop.web.delivery.dto.DeliveryForm;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class DeliveryRestController {

	private final DeliveryService deliveryService;

	@PostMapping
	public ResponseEntity<DeliveryDTO> newDelivery(@RequestBody DeliveryForm deliveryForm) {
		Delivery delivery = deliveryService.save(Delivery.convertToDelivery(deliveryForm));

		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.build()
			.toUri();

		return ResponseEntity.created(location).body(new DeliveryDTO(delivery));
	}

	@GetMapping(value = "member/{memberId}/delivery/{deliveryId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DeliveryApiResult> findDeliveryById(@PathVariable Long memberId, @PathVariable Long deliveryId) {
		return ResponseEntity.ok(deliveryService.findDeliveryApiResultByDeliveryId(deliveryId));
	}

	@GetMapping(value = "member/{memberId}/delivery", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DeliveryDTO>> findDeliveries(Pageable pageable) {
		List<Delivery> deliveries = deliveryService.findDeliveries(pageable);
		return ResponseEntity.ok(deliveries.stream()
			.map(DeliveryDTO::new).collect(Collectors.toList()));
	}

	@PutMapping("member/{memberId}/delivery")
	public ResponseEntity<DeliveryDTO> editDelivery(@RequestBody DeliveryForm deliveryForm, @PathVariable Long id) {
		Delivery delivery = deliveryService.updateDelivery(Delivery.convertToDelivery(deliveryForm), id);
		return ResponseEntity.ok(new DeliveryDTO(delivery));
	}

	@PatchMapping("member/{memberId}/delivery/{deliveryId}/status")
	public ResponseEntity<Boolean>  requestUpdateDeliveryStatus(@RequestBody Status status, @PathVariable Long deliveryId) {
		return ResponseEntity.ok(deliveryService.updateStatus(deliveryId, status));
	}

	@DeleteMapping("member/{memberId}/delivery")
	public ResponseEntity<Integer> deleteAssignedDeliveries(@RequestParam Status status) {
		return ResponseEntity.ok(deliveryService.removeStatusDel(status));
	}
}
