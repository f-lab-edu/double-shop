package com.project.doubleshop.web.delivery.dto;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.delivery.entity.Delivery;
import com.project.doubleshop.domain.delivery.entity.DeliveryStatus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeliveryForm {
	// 배송 pk
	private Long id;

	// 운송장 번호
	private String waybillNumber;

	// 배송 메모
	private String memo;

	// 배송 상태
	private DeliveryStatus deliveryStatus;

	// 배송정책 fk
	private Long deliveryPolicyId;

	// 배송기사 fk
	private Long deliveryDriverId;

	public DeliveryForm(Delivery source) {
		this.id = source.getId();
		this.waybillNumber = source.getWaybillNumber();
		this.memo = source.getMemo();
		this.deliveryStatus = source.getDeliveryStatus();
		this.deliveryPolicyId = source.getDeliveryPolicyId();
		this.deliveryDriverId = source.getDeliveryDriverId();
	}
}
