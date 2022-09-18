package com.project.doubleshop.web.delivery.dto;

import com.project.doubleshop.delivery.entity.DeliveryPolicy;
import com.project.doubleshop.delivery.entity.enumuration.FeeMethod;
import com.project.doubleshop.delivery.entity.enumuration.FeePolicy;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeliveryPolicyForm {
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

	public DeliveryPolicyForm(DeliveryPolicy source) {
		this.id = source.getId();
		this.name = source.getName();
		this.company = source.getCompany();
		this.feePolicy = source.getFeePolicy();
		this.feeMethod = source.getFeeMethod();
		this.feePrice = source.getFeePrice();
		this.islandMountainousFee = source.getIslandMountainousFee();
	}
}
