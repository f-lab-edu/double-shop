package com.project.doubleshop.domain.order.entity.mock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

	private Long orderId;

	private Long itemId;

	private Integer count;

	private Integer price;

	private Integer priority;
}
