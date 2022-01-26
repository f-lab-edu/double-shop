package com.project.doubleshop.web.order.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OrderItemDto {
	private Long itemId;

	private String name;

	private Integer price;

	private Integer stock;

	private Integer quantity;

	public OrderItemDto(OrderDetailResult source) {
		this.itemId = source.getItemId();
		this.name = source.getName();
		this.price = source.getPrice();
		this.stock = source.getStock();
		this.quantity = source.getQuantity();
	}
}
