package com.challenge.ivms.service;


import com.challenge.ivms.model.Order;
import com.challenge.ivms.model.OrderItem;
import com.challenge.ivms.model.OrderRequest;
import com.challenge.ivms.model.OrderResponse;
import com.challenge.ivms.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryService inventoryService;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        // Validate order items
        for (OrderItem item : orderRequest.getItems()) {
            inventoryService.validateInventory(item.getItemId(), item.getQuantity());
        }

        // Create new order
        Order order = new Order();
        order.setUserId(orderRequest.getUserId());
        order.setItems(orderRequest.getItems());

        Order savedOrder = orderRepository.save(order);

        // Update inventory
        for (OrderItem item : savedOrder.getItems()) {
            inventoryService.updateInventory(item.getItemId(), item.getQuantity());
        }

        // Return order response
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(savedOrder.getId());
        orderResponse.setUserId(savedOrder.getUserId());
        orderResponse.setItems(savedOrder.getItems());

        return orderResponse;
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<OrderResponse> orderResponses = new ArrayList<>();
        List<Order> orders = orderRepository.findAll();

        for (Order order : orders) {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setId(order.getId());
            orderResponse.setUserId(order.getUserId());
            orderResponse.setItems(order.getItems());
            orderResponses.add(orderResponse);
        }

        return orderResponses;
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order id: " + id));

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setUserId(order.getUserId());
        orderResponse.setItems(order.getItems());

        return orderResponse;
    }

    @Override
    public OrderResponse updateOrder(Long id, OrderRequest orderRequest) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order id: " + id));

        // Validate order items
        for (OrderItem item : orderRequest.getItems()) {
            inventoryService.validateInventory(item.getItemId(), item.getQuantity());
        }

        // Update order
        order.setUserId(orderRequest.getUserId());
        order.setItems(orderRequest.getItems());

        Order savedOrder = orderRepository.save(order);

        // Update inventory
        for (OrderItem item : savedOrder.getItems()) {
            inventoryService.updateInventory(item.getItemId(), item.getQuantity());
        }

        // Return order response
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(savedOrder.getId());
        orderResponse.setUserId(savedOrder.getUserId());
        orderResponse.setItems(savedOrder.getItems());

        return orderResponse;
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}

