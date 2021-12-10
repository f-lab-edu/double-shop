package com.project.doubleshop.domain.delivery.entity;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.common.Status;

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

	// 상태
	private Status status;

	// 상태 업데이트 시간
	private LocalDateTime statusUpdateTime;
}

