package com.challenge.ivms.controller;


import com.challenge.ivms.model.OrderItemRequest;
import com.challenge.ivms.model.OrderResponse;
import com.challenge.ivms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderItemRequest orderItemRequest) {
        final OrderResponse orderResponse = orderService.createOrder(orderItemRequest);
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

}