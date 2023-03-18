package com.challenge.ivms.model;


import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class OrderResponse {

    private Long id;
    private Long userId;
    private List<OrderItem> orderItems;


}