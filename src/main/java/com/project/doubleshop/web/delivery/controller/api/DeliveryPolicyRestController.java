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
import com.project.doubleshop.domain.delivery.entity.DeliveryPolicy;
import com.project.doubleshop.domain.delivery.service.DeliveryPolicyService;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.delivery.dto.DeliveryDTO;
import com.project.doubleshop.web.delivery.dto.DeliveryPolicyDTO;
import com.project.doubleshop.web.delivery.dto.DeliveryPolicyForm;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/delivery/policy")
@RequiredArgsConstructor
public class DeliveryPolicyRestController {

	private final DeliveryPolicyService deliveryPolicyService;

	@PostMapping
	public ResponseEntity<DeliveryPolicyDTO> newDeliveryPolicy(@RequestBody DeliveryPolicyForm deliveryPolicyForm) {
		DeliveryPolicy deliveryPolicy =
			deliveryPolicyService.getInsertedDeliveryPolicy(DeliveryPolicy.convertToDeliveryPolicy(deliveryPolicyForm));

		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.build()
			.toUri();

		return ResponseEntity.created(location).body(new DeliveryPolicyDTO(deliveryPolicy));
	}

	@GetMapping("{id}")
	public ResponseEntity<DeliveryPolicyDTO> findDeliveryPolicyById(@PathVariable Long id) {
		DeliveryPolicy deliveryPolicy = deliveryPolicyService.findById(id);

		return ResponseEntity.ok(new DeliveryPolicyDTO(deliveryPolicy));
	}

	@GetMapping
	public ResponseEntity<List<DeliveryPolicyDTO>> findDeliveryPolicies(Pageable pageable) {
		List<DeliveryPolicy> deliveryPolicies = deliveryPolicyService.findDeliveryPolicies(pageable);
		return ResponseEntity.ok(deliveryPolicies.stream().map(DeliveryPolicyDTO::new).collect(Collectors.toList()));
	}

	@PutMapping("{id}")
	public ResponseEntity<DeliveryPolicyDTO> editDeliveryPolicy(@RequestBody DeliveryPolicyForm deliveryPolicyForm,
		@PathVariable Long id) {
		DeliveryPolicy deliveryPolicy = deliveryPolicyService.saveDeliveryPolicy(
			DeliveryPolicy.convertToDeliveryPolicy(deliveryPolicyForm), id);
		return ResponseEntity.ok(new DeliveryPolicyDTO(deliveryPolicy));
	}

	@PatchMapping("{id}/status")
	public ResponseEntity<DeliveryPolicyDTO> requestUpdateDeliveryPolicyStatus(@RequestBody Status status, @PathVariable Long id) {
		return ResponseEntity.ok(new DeliveryPolicyDTO(deliveryPolicyService.updateDeliveryPolicyStatus(status, id)));
	}

	@DeleteMapping
	public ResponseEntity<Integer> deleteAssignedDeliveryPolicies(@RequestBody Status status) {
		return ResponseEntity.ok(deliveryPolicyService.deleteDeliveryPolices(status));
	}
}
