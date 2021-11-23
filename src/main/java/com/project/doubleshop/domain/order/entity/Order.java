package com.project.doubleshop.domain.order.entity;

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

    private Long couponId; // 쿠폰

    private List<OrderItem> orderItems = new ArrayList<>(); // 주문 상품

//    private Delivery delivery; // 배송 정보

    private LocalDateTime orderDate; // 주문일자

    private OrderStatus status; // 주문 상태

    private Long totalPrice; // 총 상품 가격

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    /* 주문 생성 메서드 */
    public static Order createOrder(Member member, /*Delivery delivery,*/ OrderItem ... orderItems) {
        Order order = new Order();
        order.setMember(member);
//        order.setDelivery(delivery);

        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }

        order.setStatus(OrderStatus.ORDERED);
        order.setOrderDate(LocalDateTime.now());

        return order;
    }

    /* 비즈니스 로직 */
    public void cancel() {
        if (false /*delivery.getStatus() == DeliveryStatus.COMPLETE*/) {
            throw new IllegalStateException("배송 완료된 상품은 취소할 수 없습니다.");
        }

        this.setStatus(OrderStatus.CANCELED);
        orderItems.forEach(OrderItem::cancel);
        // 주문에 담은 각 상품들에 cancel을 날려주어 상품 도메인에 각 상품의 개수를 더한다.
    }

    // orderItems에 들어 있는 상품들 각각의 수량과 가격을 계산해주어야 하기 때문에,
    // OrderItem에도 조회 로직을 추가한다.
    public Long getTotalPrice() {
        return orderItems
                .stream()
                .mapToLong(OrderItem::getTotalPrice)
                .sum();
    }
    
}
