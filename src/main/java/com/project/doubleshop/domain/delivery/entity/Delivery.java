package com.project.doubleshop.domain.delivery.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.common.StatusConverter;
import com.project.doubleshop.domain.delivery.entity.enumuration.DeliveryStatus;
import com.project.doubleshop.domain.delivery.entity.enumuration.converter.DeliveryStatusConverter;
import com.project.doubleshop.domain.delivery.entity.legacy.DeliveryDriver;
import com.project.doubleshop.web.delivery.dto.DeliveryForm;

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
public class Delivery {
	// 배송 pk
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 운송장 번호
	private String waybillNumber;

	// 배송 메모
	private String memo;

	// 배송 등록일
	@Column(insertable = false, updatable = false,
		columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime createTime;

	// 배송 수정일
	@Column(insertable = false, updatable = false,
		columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime updateTime;

	// 배송 상태
	@Convert(converter = DeliveryStatusConverter.class)
	private DeliveryStatus deliveryStatus;

	// 상태
	@Convert(converter = StatusConverter.class)
	private Status status;

	// 상태 업데이트 시간
	@Column(insertable = false, updatable = false,
		columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime statusUpdateTime;

	// 배송정책 fk
	@OneToOne(fetch = FetchType.LAZY)
	private DeliveryPolicy deliveryPolicy;

	public static Delivery convertToDelivery(DeliveryForm form) {
		return Delivery.builder()
			.id(form.getId())
			.waybillNumber(form.getWaybillNumber())
			.memo(form.getMemo())
			.deliveryStatus(form.getDeliveryStatus())
			.deliveryPolicy(form.getDeliveryPolicy())
			.build();
	}
}

