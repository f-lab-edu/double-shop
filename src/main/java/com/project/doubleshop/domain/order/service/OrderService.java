package com.project.doubleshop.domain.order.service;

import org.springframework.stereotype.Service;

import com.project.doubleshop.domain.address.repository.AddressRepository;
import com.project.doubleshop.domain.order.repository.OrderDetailRepository;
import com.project.doubleshop.domain.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final OrderRepository orderRepository;
	private final OrderDetailRepository orderDetailRepository;
	private final AddressRepository addressRepository;
}
