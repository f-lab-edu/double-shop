package com.project.doubleshop.domain.delivery.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.web.delivery.dto.DeliveryForm;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Delivery {
	// 배송 pk
	private Long id;

	// 운송장 번호
	private String waybillNumber;

	// 배송 메모
	private String memo;

	// 배송 등록일
	private LocalDateTime createTime;

	// 배송 수정일
	private LocalDateTime updateTime;

	// 배송 상태
	private DeliveryStatus deliveryStatus;

	// 상태
	private Status status;

	// 상태 업데이트 시간
	private LocalDateTime statusUpdateTime;

	// 주문 fk
	private Long orderId;

	// 배송정책 fk
	private Long deliveryPolicyId;

	// 배송기사 fk
	private Long deliveryDriverId;

	public static Delivery convertToDelivery(DeliveryForm form) {
		return Delivery.builder()
			.id(form.getId())
			.waybillNumber(form.getWaybillNumber())
			.memo(form.getMemo())
			.deliveryStatus(form.getDeliveryStatus())
			.deliveryPolicyId(form.getDeliveryPolicyId())
			.deliveryDriverId(form.getDeliveryDriverId())
			.build();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Delivery delivery = (Delivery)o;
		return id.equals(delivery.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}

