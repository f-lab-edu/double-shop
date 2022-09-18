package com.project.doubleshop.delivery.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.project.doubleshop.common.Status;
import com.project.doubleshop.common.StatusConverter;
import com.project.doubleshop.common.StatusManager;
import com.project.doubleshop.delivery.entity.enumuration.FeeMethod;
import com.project.doubleshop.delivery.entity.enumuration.FeePolicy;
import com.project.doubleshop.delivery.entity.enumuration.converter.FeeMethodConverter;
import com.project.doubleshop.delivery.entity.enumuration.converter.FeePolicyConverter;
import com.project.doubleshop.web.delivery.dto.DeliveryPolicyForm;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DeliveryPolicy implements StatusManager {
	// 배송 정책 pk
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 배송 정책 이름
	private String name;

	// 배송사
	private String company;

	// 배송비 적용 정책
	@Convert(converter = FeePolicyConverter.class)
	private FeePolicy feePolicy;

	// 배송비 지불방식
	@Convert(converter = FeeMethodConverter.class)
	private FeeMethod feeMethod;

	// 배송비
	private int feePrice;

	// 산간도서지역비용
	private int islandMountainousFee;

	// 상태
	@Convert(converter = StatusConverter.class)
	private Status status;

	// 상태 업데이트 시간
	@Column(insertable = false, updatable = false,
		columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime statusUpdateTime;

	@Override
	public void saveStatus(Status status) {
		this.status = status;
	}
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