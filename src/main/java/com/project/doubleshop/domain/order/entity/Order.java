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
public class Order {

	private Long id;

	private LocalDateTime orderedTime;

	private Integer orderStatus;

	private Integer orderType;

	private Integer totalPrice;

	private Status status;

	private LocalDateTime statusUpdateTime;

	private Long addressId;

	private Long memberId;
}
