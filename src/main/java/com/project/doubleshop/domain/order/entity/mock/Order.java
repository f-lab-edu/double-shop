package com.project.doubleshop.domain.order.entity.mock;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.common.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	private Long id;

	private Long memberId;

	private LocalDateTime orderedTime;

	private Integer totalPrice;

	private boolean isCanceled;

	private Status status;

	private LocalDateTime statusUpdateTime;

	private LocalDateTime createTime;

}
