package com.project.doubleshop.web.delivery.dto;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.delivery.entity.DeliveryDriver;

import lombok.Getter;

@Getter
public class DeliveryDriverDTO {
	// 배송기사 pk
	private final Long id;

	// 이름
	private final String name;

	// 핸드폰 번호
	private final String phone;

	// 적재한도 g
	private final Integer capacity;

	// 등록일
	private final LocalDateTime createTime;

	// 수정일
	private final LocalDateTime updateTime;

	public DeliveryDriverDTO(DeliveryDriver source) {
		this.id = source.getId();
		this.name = source.getName();
		this.phone = source.getPhone();
		this.capacity = source.getCapacity();
		this.createTime = source.getCreateTime();
		this.updateTime = source.getUpdateTime();
	}
}
