package com.challenge.ivms.service;

import com.challenge.ivms.model.*;
import com.challenge.ivms.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final InvoiceService invoiceService;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductService productService, InvoiceService invoiceService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.invoiceService = invoiceService;
    }

    public OrderResponse createOrder(OrderItemRequest orderRequest) {
        final Order order = new Order();
        final List<OrderItem> orderItems = new ArrayList<>();
        final OrderItem orderItem = new OrderItem();
        for (final OrderItemRequest.OrderItemRequestItem orderItemRequest : orderRequest.getOrderItems()) {
            final Product product = productService.getProductById(orderItemRequest.getProductId());
            final int availableQuantity = product.getQuantity();

            if (availableQuantity >= orderItemRequest.getQuantity()) {
                final int updatedQuantity = availableQuantity - orderItemRequest.getQuantity();
                product.setQuantity(updatedQuantity);
                productService.updateProduct(product.getId(), product);


                orderItem.setItemId(product.getId());
                orderItem.setQuantity(orderItemRequest.getQuantity());
                orderItem.setPrice(product.getPrice());
                orderItems.add(orderItem);
            } else {
                // Notify the customer and suggest alternative products
            }
        }

        order.setUserId(orderRequest.getUserId());
        order.setOrderItems(orderItems);
        final Order savedOrder = orderRepository.save(order);

        return new OrderResponse(savedOrder.getId(), savedOrder.getUserId(), savedOrder.getOrderItems());
    }
}