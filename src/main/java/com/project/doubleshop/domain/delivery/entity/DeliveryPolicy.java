package com.project.doubleshop.domain.delivery.entity;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.web.delivery.dto.DeliveryPolicyForm;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class DeliveryPolicy {
	// 배송 정책 pk
	private Long id;

	// 배송 정책 이름
	private String name;

	// 배송사
	private String company;

	// 배송비 적용 정책
	private FeePolicy feePolicy;

	// 배송비 지불방식
	private FeeMethod feeMethod;

	// 배송비
	private int feePrice;

	// 산간도서지역비용
	private int islandMountainousFee;

	// 상태
	private Status status;

	// 상태 업데이트 시간
	private LocalDateTime statusUpdateTime;

	public static DeliveryPolicy convertToDeliveryPolicy(DeliveryPolicyForm form) {
		return DeliveryPolicy.builder()
			.id(form.getId())
			.name(form.getName())
			.company(form.getCompany())
			.feePolicy(form.getFeePolicy())
			.feeMethod(form.getFeeMethod())
			.feePrice(form.getFeePrice())
			.islandMountainousFee(form.getIslandMountainousFee())
			.build();
	}
}