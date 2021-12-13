package com.project.doubleshop.web.delivery.dto;

import com.project.doubleshop.domain.delivery.entity.Delivery;
import com.project.doubleshop.domain.delivery.entity.DeliveryDriver;
import com.project.doubleshop.domain.delivery.entity.DeliveryPolicy;
import com.project.doubleshop.domain.delivery.entity.DeliveryStatus;
import com.project.doubleshop.web.delivery.dto.DeliveryDriverDTO;
import com.project.doubleshop.web.delivery.dto.DeliveryPolicyDTO;

import lombok.Getter;

@Getter
public class DeliveryApiResult {
	// 배송 pk
	private final Long id;

	// 운송장 번호
	private final String waybillNumber;

	// 배송 메모
	private final String memo;

	// 배송 상태
	private final DeliveryStatus deliveryStatus;

	// 배송정책 fk
	private final DeliveryPolicyDTO deliveryPolicy;

	// 배송기사 fk
	private final DeliveryDriverDTO deliveryDriver;

	public DeliveryApiResult(Delivery delivery, DeliveryPolicy deliveryPolicy, DeliveryDriver deliveryDriver) {
		this.id = delivery.getId();
		this.waybillNumber = delivery.getWaybillNumber();
		this.memo = delivery.getMemo();
		this.deliveryStatus = delivery.getDeliveryStatus();
		this.deliveryPolicy = new DeliveryPolicyDTO(deliveryPolicy);
		this.deliveryDriver = new DeliveryDriverDTO(deliveryDriver);
	}
}
