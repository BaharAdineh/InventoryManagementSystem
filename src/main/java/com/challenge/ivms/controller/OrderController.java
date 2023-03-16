package com.challenge.ivms.controller;


import com.challenge.ivms.errorHandling.ResourceNotFoundException;
import com.challenge.ivms.model.*;
import com.challenge.ivms.repository.OrderRepository;
import com.challenge.ivms.service.InvoiceService;
import com.challenge.ivms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private OrderRepository orderRepository;

    public OrderResponse createOrder(OrderItemRequest orderRequest) {
        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequest orderItemRequest : orderRequest.getOrderItems()) {
            Product product = productService.getProductById(orderItemRequest.getProductId());
            int availableQuantity = product.getQuantity();

            if (availableQuantity >= orderItemRequest.getQuantity()) {
                int updatedQuantity = availableQuantity - orderItemRequest.getQuantity();
                product.setQuantity(updatedQuantity);
                productService.updateProduct(product.getId(), product);

                OrderItem orderItem = new OrderItem();
                orderItem.setProductId(product.getId());
                orderItem.setQuantity(orderItemRequest.getQuantity());
                orderItem.setPrice(product.getPrice());
                orderItems.add(orderItem);
            } else {
                // Notify the customer and suggest alternative products
            }
        }

        order.setOrderItems(orderItems);
        Order savedOrder = orderRepository.save(order);

        Invoice invoice = invoiceService.generateInvoice(savedOrder);
        // Send the invoice to the customer

        return new OrderResponse(savedOrder.getId(), savedOrder.getOrderItems());
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order : orders) {
            OrderResponse orderResponse = new OrderResponse(order.getId(), order.getOrderItems());
            orderResponses.add(orderResponse);
        }

        return orderResponses;
    }

    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));

        OrderResponse orderResponse = new OrderResponse(order.getId(), order.getOrderItems());
        return orderResponse;
    }

    public OrderResponse updateOrder(Long id, OrderItemRequest orderRequest) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequest orderItemRequest : orderRequest.getOrderItems()) {
            Product product = productService.getProductById(orderItemRequest.getProductId());
            int availableQuantity = product.getQuantity();

            if (availableQuantity >= orderItemRequest.getQuantity()) {
                int updatedQuantity = availableQuantity - orderItemRequest.getQuantity();
                product.setQuantity(updatedQuantity);
                productService.updateProduct(product.getId(), product);

                OrderItem orderItem = new OrderItem();
                orderItem.setProductId(product.getId());
                orderItem.setQuantity(orderItemRequest.getQuantity());
                orderItem.setPrice(product.getPrice());
                orderItems.add(orderItem);
            } else {
                // Notify the customer and suggest alternative products
            }
        }

        order.setOrderItems(orderItems);
        Order savedOrder = orderRepository.save(order);

        Invoice invoice = invoiceService.generateInvoice(savedOrder);
        // Send the invoice to the customer

        return new OrderResponse(savedOrder.getId(), savedOrder.getOrderItems());
    }

    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));

        orderRepository.delete(order);
    }

}