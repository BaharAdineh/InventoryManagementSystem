package com.challenge.ivms.model;


import java.util.Date;
import java.util.List;
import lombok.Data;
@Data
public class OrderResponse {

    private Long id;
    private Long userId;
    private List<OrderItem> items;
}