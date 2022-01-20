package com.project.doubleshop.web.order.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OrderDetailResult {
	private Long orderId;

	private Long itemId;

	String name;

	Integer price;

	Integer stock;
}
