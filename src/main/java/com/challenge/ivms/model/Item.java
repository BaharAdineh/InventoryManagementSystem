package com.challenge.ivms.model;

import lombok.Data;

@Data
public class Item {
    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private Double price;
}
