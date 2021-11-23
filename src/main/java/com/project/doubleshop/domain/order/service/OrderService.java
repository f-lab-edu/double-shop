package com.project.doubleshop.domain.order.service;

import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.repository.ItemRepository;
import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.domain.member.repository.MemberInfoRepository;
import com.project.doubleshop.domain.order.entity.Order;
import com.project.doubleshop.domain.order.entity.OrderItem;
import com.project.doubleshop.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final MemberInfoRepository memberInfoRepository;

    private final ItemRepository itemRepository;

    // 주문 생성 메서드
    @Transactional
    public Long order(String userId, Long itemId, int count) {
        // 엔티티 조회
        Optional<Member> member = memberInfoRepository.findByUserId(userId);
        Item item = itemRepository.findById(itemId);

        // 배송 정보 생성
//        Delivery delivery = new Delivery();
//        delivery.setAddress(member.getAddress());

        // 주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member.get(), orderItem);

        // 주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    // 주문 취소 메서드
    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findById(orderId);

        // 주문 취소
        order.cancel();
    }

    // 검색

    /*
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderSearch;
    }
     */

}
