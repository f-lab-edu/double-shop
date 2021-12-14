package com.project.doubleshop.web.delivery.dto;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.delivery.entity.DeliveryDriver;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeliveryDriverForm {
	// 배송기사 pk
	private Long id;

	// 이름
	private String name;

	// 핸드폰 번호
	private String phone;

	public DeliveryDriverForm(DeliveryDriver source) {
		this.id = source.getId();
		this.name = source.getName();
		this.phone = source.getPhone();
	}
}
