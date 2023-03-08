package com.challenge.ivms.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private Long itemId;
    private String itemName;
    private Double price;
    private Integer quantity;
}

