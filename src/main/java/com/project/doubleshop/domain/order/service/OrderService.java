package com.project.doubleshop.domain.order.service;

import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.repository.ItemRepository;
import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.domain.member.repository.AuthMemberRepository;
import com.project.doubleshop.domain.order.entity.Order;
import com.project.doubleshop.domain.order.entity.OrderItem;
import com.project.doubleshop.domain.order.repository.OrderRepository;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.order.dto.OrderDto;
import com.project.doubleshop.web.order.dto.OrderHistoryDto;
import com.project.doubleshop.web.order.dto.OrderItemDto;
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

    public List<OrderHistoryDto> findOrders(String userId, Pageable pageable) {
        // 주문 목록 조회
        List<Order> orders = orderRepository.findAll(userId, pageable);

        List<OrderHistoryDto> orderHistoryDtos = new ArrayList<>();

        // 주문 리스트를 순회하면서 구매 내역 페이지에 전달할 DTO 생성
        for (Order order : orders) {
            OrderHistoryDto orderHistoryDto = new OrderHistoryDto(order);
            List<OrderItem> orderItems = order.getOrderItems();

            for (OrderItem orderItem : orderItems) {
                OrderItemDto orderItemDto = new OrderItemDto(orderItem);
                orderHistoryDto.addOrderItemDto(orderItemDto);
            }

            orderHistoryDtos.add(orderHistoryDto);
        }

        return orderHistoryDtos;
    }

    public boolean validateOrder(Long orderId, String userId) {
        Member member = authMemberRepository.findByUserId(userId);
        Order order = orderRepository.findById(orderId);
        Member savedMember = order.getMember();

        if (!member.getUserId().equals(savedMember.getUserId())) {
            return false;
        }

        return true;
    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId);
        order.cancelOrder();
    }

}
