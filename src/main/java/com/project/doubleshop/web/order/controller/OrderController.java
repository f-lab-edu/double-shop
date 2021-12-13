package com.project.doubleshop.web.order.controller;

import com.project.doubleshop.domain.order.service.OrderService;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.config.support.SimplePageRequest;
import com.project.doubleshop.web.order.dto.OrderDto;
import com.project.doubleshop.web.order.dto.OrderHistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("order")
    public ResponseEntity order(@RequestBody OrderDto orderDto, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userId = userDetails.getUsername(); // 로그인한 회원의 ID 정보 조회

        Long orderId;

        try {
            orderId = orderService.order(userId, orderDto);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }

    @GetMapping({"orders", "orders/{page}"})
    public ResponseEntity orderHistory(@PathVariable("page") Optional<Integer> page,
                               Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Pageable pageable = new SimplePageRequest(page.orElse(0), 4);

        List<OrderHistoryDto> orderHistoryDtoList = orderService.findOrders(userDetails.getUsername(), pageable);

        return new ResponseEntity<List<OrderHistoryDto>>(orderHistoryDtoList, HttpStatus.OK);
    }

    @PostMapping("order/{orderId}/cancel")
    public ResponseEntity cancelOrder(@PathVariable("orderId") Long orderId, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (!orderService.validateOrder(orderId, userDetails.getUsername())) {
            return new ResponseEntity<String>("권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        orderService.cancelOrder(orderId);

        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }

}
