package com.project.doubleshop.web.order.dto;

import java.time.LocalDateTime;

import com.project.doubleshop.address.entity.Address;
import com.project.doubleshop.common.Status;
import com.project.doubleshop.member.domain.Member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OrderForm {

	private LocalDateTime orderedTime;

	private Integer orderStatus;

	private Integer orderType;

	private Integer totalPrice;

	private Status status;

	private LocalDateTime statusUpdateTime;

	private Address address;

	private Member member;
}
