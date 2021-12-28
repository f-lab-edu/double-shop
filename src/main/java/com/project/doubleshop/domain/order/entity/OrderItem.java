package com.project.doubleshop.domain.order.entity;

import com.project.doubleshop.domain.item.entity.Item;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OrderItem {

    private Long orderId; // 주문 ID

    private Item item; // 상품 정보

    private Order order; // 주문 정보

    private Integer count; // 구매 수량

    private Integer price; // 구매가

    /* 생성 메서드 */
    public static OrderItem createOrderItem(Item item, int count) {
        OrderItem orderItem = new OrderItem();

        orderItem.setItem(item);
        orderItem.setCount(count);

        orderItem.setPrice(item.getPrice()); // 상품 가격을 주문 가격으로 설정

        item.decreaseStock(count); // 주문 수량만큼 재고 수량을 감소

        return orderItem;
    }

    public void cancel() { // 재고 증가
        getItem().increaseStock(count);
    }

    public int getTotalPrice() {
        return price * count; // 주문 가격과 수량을 곱하여 주문 총 가격을 계산
    }

}
