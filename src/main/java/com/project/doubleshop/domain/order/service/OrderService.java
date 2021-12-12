package com.project.doubleshop.domain.order.service;

import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.repository.ItemRepository;
import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.domain.member.repository.AuthMemberRepository;
import com.project.doubleshop.domain.order.entity.Order;
import com.project.doubleshop.domain.order.entity.OrderItem;
import com.project.doubleshop.domain.order.repository.OrderRepository;
import com.project.doubleshop.web.order.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final AuthMemberRepository authMemberRepository;

    private final ItemRepository itemRepository;

    // 주문 생성 메서드
    @Transactional
    public Long order(String userId, OrderDto orderDto) {
        // 엔티티 조회
        Member member = authMemberRepository.findByUserId(userId);
        Item item = itemRepository.findById(orderDto.getItemId());

        List<OrderItem> orderItems = new ArrayList<>();

        // 주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
        orderItems.add(orderItem);

        // 주문 생성
        Order order = Order.createOrder(member, orderItems);

        // 주문 저장
        orderRepository.save(order);

        return order.getId();
    }

}
