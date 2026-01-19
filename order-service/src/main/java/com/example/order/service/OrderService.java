package com.example.order.service;

import com.example.order.entity.Order;
import com.example.order.model.OrderStatus;
import com.example.order.repository.OrderRepository;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order createOrder(BigDecimal amount) {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID());
        order.setStatus(OrderStatus.NEW);
        order.setAmount(amount);
        return orderRepository.save(order);
    }
}
