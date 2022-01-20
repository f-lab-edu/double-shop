package com.project.doubleshop.web.order.dto;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.common.Status;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OrderStatusRequest {
	private Long id;

	private Integer orderStatus;

	private Status status;

	private LocalDateTime statusUpdateTime;

	private Long memberId;
}
