package com.project.doubleshop.web.order.dto;

import com.project.doubleshop.domain.order.entity.Order;
import com.project.doubleshop.domain.order.entity.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderHistoryDto {

    private Long orderId;

    private String orderDate;

    private OrderStatus orderStatus;

    private List<OrderItemDto> orderItemDtoList = new ArrayList<>(); // 주문 상품 리스트

    public OrderHistoryDto(Order order) {
        this.orderId = order.getId();
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = order.getOrderStatus();
    }

    public void addOrderItemDto(OrderItemDto orderItemDto) {
        orderItemDtoList.add(orderItemDto); // DTO 객체를 주문 상품 리스트에 추가
    }

}
