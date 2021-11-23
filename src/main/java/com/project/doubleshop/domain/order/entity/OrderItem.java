package com.project.doubleshop.domain.order.entity;

import com.project.doubleshop.domain.item.entity.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderItem {

    private Long orderId; // 주문 ID

    private Item item; // 상품 정보

    private Order order; // 주문 정보

    private Integer count; // 구매 수량

    private Integer price; // 구매가

    /* 생성 메서드 */
    public static OrderItem createOrderItem(Item item, int price, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setPrice(price);
        orderItem.setCount(count);

        item.decreaseStock(count);

        return orderItem;
    }

    /* 비즈니스 로직 */
    public void cancel() { // 재고 증가
        getItem().increaseStock(count);
    }

    public int getTotalPrice() {
        return getPrice() * getCount();
    }

}
