package com.example.order.service;

import com.example.order.entity.Order;
import com.example.order.exception.InvalidOrderException;
import com.example.order.exception.OrderNotFoundException;
import com.example.order.model.OrderStatus;
import com.example.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.UUID;
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order createOrder(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.valueOf(10000)) > 0) {
            throw new InvalidOrderException("Order amount exceeds allowed limit");
        }

        Order order = new Order();
        order.setStatus(OrderStatus.NEW);
        order.setAmount(amount);

        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public Order getOrderById(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }



}
