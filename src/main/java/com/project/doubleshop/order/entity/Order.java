package com.project.doubleshop.order.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.doubleshop.address.entity.Address;
import com.project.doubleshop.common.Status;
import com.project.doubleshop.common.StatusConverter;
import com.project.doubleshop.common.StatusManager;
import com.project.doubleshop.member.domain.Member;
import com.project.doubleshop.web.order.dto.OrderForm;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="`order`")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Order implements StatusManager {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime orderedTime;

	private Integer orderStatus;

	private Integer orderType;

	private Integer totalPrice;

	@Convert(converter = StatusConverter.class)
	private Status status;

	@Column(insertable = false, updatable = false,
		columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime statusUpdateTime;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Address address;

	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public void saveStatus(Status status) {
		this.status = status;
	}

	public static Order convertToOrder(OrderForm orderForm) {
		return Order.builder()
			.orderedTime(orderForm.getOrderedTime())
			.orderStatus(orderForm.getOrderStatus())
			.totalPrice(orderForm.getTotalPrice())
			.status(orderForm.getStatus())
			.statusUpdateTime(orderForm.getStatusUpdateTime())
			.address(orderForm.getAddress())
			.member(orderForm.getMember())
			.build();
	}
}
