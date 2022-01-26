package com.project.doubleshop.domain.order.entity;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.common.Status;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OrderDetail {
	private Long orderId;

	private Long itemId;

	private Integer quantity;

	private Integer price;

	private Status status;

	private LocalDateTime statusUpdateTime;
}
