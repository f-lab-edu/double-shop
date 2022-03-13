package com.project.doubleshop.web.delivery.dto;

import com.project.doubleshop.domain.delivery.entity.Delivery;
import com.project.doubleshop.domain.delivery.entity.DeliveryPolicy;
import com.project.doubleshop.domain.delivery.entity.enumuration.DeliveryStatus;

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
	private DeliveryPolicy deliveryPolicy;

	// 배송기사 fk
	private Long deliveryDriverId;

	public DeliveryForm(Delivery source) {
		this.id = source.getId();
		this.waybillNumber = source.getWaybillNumber();
		this.memo = source.getMemo();
		this.deliveryStatus = source.getDeliveryStatus();
		this.deliveryPolicy = source.getDeliveryPolicy();
	}
}
