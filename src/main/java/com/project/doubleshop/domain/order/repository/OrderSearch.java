package com.project.doubleshop.domain.order.repository;

import com.project.doubleshop.domain.order.entity.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

    private String memberName;

    private OrderStatus orderStatus;

}
