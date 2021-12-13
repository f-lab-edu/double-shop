package com.project.doubleshop.web.delivery.dto;

import com.project.doubleshop.domain.delivery.entity.DeliveryPolicy;
import com.project.doubleshop.domain.delivery.entity.FeeMethod;
import com.project.doubleshop.domain.delivery.entity.FeePolicy;

import lombok.Getter;

@Getter
public class DeliveryPolicyDTO {
	// 배송 정책 pk
	private final Long id;

	// 배송 정책 이름
	private final String name;

	// 배송사
	private final String company;

	// 배송비 적용 정책
	private final FeePolicy feePolicy;

	// 배송비 지불방식
	private final FeeMethod feeMethod;

	// 배송비
	private final int feePrice;

	// 산간도서지역비용
	private final int islandMountainousFee;

	public DeliveryPolicyDTO(DeliveryPolicy source) {
		this.id = source.getId();
		this.name = source.getName();
		this.company = source.getCompany();
		this.feePolicy = source.getFeePolicy();
		this.feeMethod = source.getFeeMethod();
		this.feePrice = source.getFeePrice();
		this.islandMountainousFee = source.getIslandMountainousFee();
	}
}
