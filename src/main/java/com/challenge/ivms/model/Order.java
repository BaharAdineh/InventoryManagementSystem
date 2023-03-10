package com.challenge.ivms.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {

    private Long id;
    private Long userId;
    private List<OrderItem> items;
}

