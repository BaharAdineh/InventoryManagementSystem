package com.challenge.ivms.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "items")
public class Item {
    @Id
    private String id;
    private String name;
    private int quantity;
    private double price;
    private String location;
}
