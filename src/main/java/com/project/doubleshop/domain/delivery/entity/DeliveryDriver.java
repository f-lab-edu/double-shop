package com.project.doubleshop.domain.delivery.entity;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.web.delivery.dto.DeliveryDriverForm;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class DeliveryDriver {
	// 배송기사 pk
	private Long id;

	// 이름
	private String name;

	// 핸드폰 번호
	private String phone;

	// 물건 적재한도 g
	private Integer capacity;

	// 등록일
	private LocalDateTime createTime;

	// 수정일
	private LocalDateTime updateTime;

	// 상태
	private Status status;

	// 상태 변경시간
	private LocalDateTime statusUpdateTime;

	public static DeliveryDriver convertToDeliveryDriver(DeliveryDriverForm form) {
		return DeliveryDriver.builder()
			.id(form.getId())
			.name(form.getName())
			.phone(form.getPhone())
			.capacity(form.getCapacity())
			.build();
	}

	public void decreaseCapacity(Integer capacity) {
		if (capacity == null) {
			throw new NullPointerException("Capacity must be provided.");
		} else if (this.capacity < capacity) {
			throw new IllegalArgumentException("Capacity must be less than before");
		}
		this.capacity -= capacity;
	}
}