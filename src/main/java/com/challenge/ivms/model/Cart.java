package com.challenge.ivms.model;

import lombok.Data;

import java.util.List;

@Data
public class Cart {
    private Long id;
    private List<Item> items;
    private Double totalPrice;
}
