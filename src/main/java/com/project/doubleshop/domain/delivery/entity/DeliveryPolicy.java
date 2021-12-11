package com.project.doubleshop.domain.delivery.entity;

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
}