package com.challenge.ivms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    private String orderId;

    private String customerName;

    private String address;

    private double totalAmount;

    private List<InvoiceItem> invoiceItems;

    private LocalDateTime createdAt;

}

