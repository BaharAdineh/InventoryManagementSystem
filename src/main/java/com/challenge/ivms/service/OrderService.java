package com.challenge.ivms.service;

import com.challenge.ivms.model.*;
import com.challenge.ivms.repository.OrderRepository;
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
    private OrderRepository orderRepository; // added reference to order repository

    public void processOrder(Order order) {
        List<OrderItem> orderItems = order.getOrderItems();

        for (OrderItem orderItem : orderItems) {
            Product product = productService.getProductById(orderItem.getItemId());
            int availableQuantity = product.getQuantity();

            if (availableQuantity >= orderItem.getQuantity()) {
                int updatedQuantity = availableQuantity - orderItem.getQuantity();
                product.setQuantity(updatedQuantity);
                productService.updateProduct(product.getId(), product);
            } else {
                // Notify the customer and suggest alternative products
            }
        }

        Invoice invoice = invoiceService.generateInvoice(order);
        // Send the invoice to the customer
    }

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
                orderItem.setItemId(product.getId());
                orderItem.setQuantity(orderItemRequest.getQuantity());
                orderItem.setPrice(product.getPrice());
                orderItems.add(orderItem);
            } else {
                // Notify the customer and suggest alternative products
            }
        }

        order.setUserId(orderRequest.getUserId()); // added setting of userId in order object
        order.setOrderItems(orderItems);
        Order savedOrder = orderRepository.save(order);

        Invoice invoice = invoiceService.generateInvoice(savedOrder);
        // Send the invoice to the customer

        return new OrderResponse(savedOrder.getId(), savedOrder.getUserId(), savedOrder.getOrderItems());
    }

}