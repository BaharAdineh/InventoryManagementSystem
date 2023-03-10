package com.challenge.ivms.service;



import com.challenge.ivms.model.OrderRequest;
import com.challenge.ivms.model.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    OrderResponse createOrder(OrderRequest orderRequest);

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(Long id);

    OrderResponse updateOrder(Long id, OrderRequest orderRequest);

    void deleteOrder(Long id);

}


