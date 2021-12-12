package com.project.doubleshop.web.order.dto;

import com.project.doubleshop.domain.order.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {

    private String itemName;

    private int count;

    private int orderPrice;

    public OrderItemDto(OrderItem orderItem) {
        this.itemName = orderItem.getItem().getName();
        this.count = orderItem.getCount();
        this.orderPrice = orderItem.getPrice();
    }

}
