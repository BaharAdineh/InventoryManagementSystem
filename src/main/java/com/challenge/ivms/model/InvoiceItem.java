package com.challenge.ivms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItem {
    private String productName;
    private int quantity;
    private double price;
    private double total;
}

