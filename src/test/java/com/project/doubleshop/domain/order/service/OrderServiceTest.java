package com.project.doubleshop.domain.order.service;

import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.repository.ItemRepository;
import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.domain.member.repository.AuthMemberRepository;
import com.project.doubleshop.domain.order.entity.Order;
import com.project.doubleshop.domain.order.entity.OrderItem;
import com.project.doubleshop.domain.order.entity.OrderStatus;
import com.project.doubleshop.domain.order.repository.OrderRepository;
import com.project.doubleshop.web.order.dto.OrderDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private AuthMemberRepository authMemberRepository;

    public Item createItem() {
        Item item = Item.builder()
                .id(1L)
                .name("EVO400 인티앰프")
                .description("조용하고 안정적인 진공관 인티앰프")
                .brandName("PrimaLuna")
                .price(7000000)
                .build();

        itemRepository.save(item);

        return item;
    }

    public Member createMember() {
        Member member = Member.builder()
                .id(1L)
                .userId("john_doe")
                .password("12345")
                .name("John Doe")
                .email("johndoe@email.com")
                .phone("01012345678")
                .build();

        authMemberRepository.save(member);

        return member;
    }

    @Test
    @DisplayName("주문 테스트")
    void order() {
        Item item = createItem();
        Member member = createMember();

        OrderDto orderDto = new OrderDto();
        orderDto.setItemId(item.getId());
        orderDto.setCount(10);

        Long orderId = orderService.order(member.getUserId(), orderDto);

        Order order = orderRepository.findById(orderId);

        List<OrderItem> orderItems = order.getOrderItems();

        int totalPrice = orderDto.getCount() * item.getPrice();

        assertEquals(totalPrice, order.getTotalPrice());
    }

    @Test
    @DisplayName("주문 취소 테스트")
    void cancelOrder() {
        Item item = createItem();
        Member member = createMember();

        OrderDto orderDto = new OrderDto();
        orderDto.setCount(5);
        orderDto.setItemId(item.getId());
        Long orderId = orderService.order(member.getUserId(), orderDto);

        Order order = orderRepository.findById(orderId);
        orderService.cancelOrder(orderId);

        assertEquals(OrderStatus.CANCELED, order.getOrderStatus());
        assertEquals(5, item.getStock());
    }

}
