package com.challenge.ivms.orderservice.service;

import com.challenge.ivms.orderservice.model.Order;
import com.challenge.ivms.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;


    public Order createOrder(String productId, String userId) {
        Order order = new Order();
        order.setProductId(productId);
        order.setUserId(userId);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }

    // Additional methods for order management as needed
}
