package com.project.doubleshop.domain.order.entity;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {

    private Long id; // 주문 PK

    private Member member; // 회원 정보

    private List<OrderItem> orderItems = new ArrayList<>(); // 주문 상품

    private LocalDateTime orderDate; // 주문일자

    private OrderStatus orderStatus; // 주문 상태

    private Long totalPrice; // 총 상품 가격

    private Status status; // 상태

    private LocalDateTime statusUpdateTime; // 상태 업데이트 시간

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    /* 주문 생성 메서드 */
    public static Order createOrder(Member member, List<OrderItem> orderItems) {
        Order order = new Order();
        order.setMember(member);

        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem); // 여러 개의 상품을 주문할 수 있도록 Order 객체에 orderItem 추가
        }

        order.setOrderStatus(OrderStatus.ORDERED);
        order.setOrderDate(LocalDateTime.now());

        return order;
    }

    // 총 주문 가격 계산
    public int getTotalPrice() {
        return orderItems
                .stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }

    public void cancelOrder() {
        orderStatus = OrderStatus.CANCELED;

        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }
    
}
