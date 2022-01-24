package com.project.doubleshop.web.order.dto;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.common.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderForm {

	private final LocalDateTime orderedTime;

	private final Integer orderStatus;

	private final Integer orderType;

	private final Integer totalPrice;

	private final Status status;

	private final LocalDateTime statusUpdateTime;

	private final Long addressId;

	private final Long memberId;
}
