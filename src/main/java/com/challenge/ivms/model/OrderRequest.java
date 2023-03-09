package com.challenge.ivms.model;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderRequest {
    private Long userId;
    private Date orderDate;
    private List<OrderItem> items;
}

