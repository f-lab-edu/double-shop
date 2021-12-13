package com.project.doubleshop.web.delivery.dto;

import com.project.doubleshop.domain.delivery.entity.Delivery;
import com.project.doubleshop.domain.delivery.entity.DeliveryStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class DeliveryDTO {
	// 배송 pk
	private final Long id;

	// 운송장 번호
	private final String waybillNumber;

	// 배송 메모
	private final String memo;

	// 배송 상태
	private final DeliveryStatus deliveryStatus;

	// 배송정책 fk
	private final Long deliveryPolicyId;

	// 배송기사 fk
	private final Long deliveryDriverId;

	public DeliveryDTO(Delivery source) {
		this.id = source.getId();
		this.waybillNumber = source.getWaybillNumber();
		this.memo = source.getMemo();
		this.deliveryStatus = source.getDeliveryStatus();
		this.deliveryPolicyId = source.getDeliveryPolicyId();
		this.deliveryDriverId = source.getDeliveryDriverId();
	}
}
