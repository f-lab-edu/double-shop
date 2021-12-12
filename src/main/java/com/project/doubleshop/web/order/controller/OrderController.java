package com.project.doubleshop.web.order.controller;

import com.project.doubleshop.domain.order.service.OrderService;
import com.project.doubleshop.web.order.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "order")
    public ResponseEntity order(@RequestBody OrderDto orderDto, Principal principal) {
        String userId = principal.getName(); // 로그인한 회원의 ID 정보 조회

        Long orderId;

        try {
            orderId = orderService.order(userId, orderDto);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }

}
