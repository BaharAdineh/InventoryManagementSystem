package com.challenge.ivms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    private String orderId;
    private String customerName;
    private String address;
    private double totalAmount;
    private LocalDateTime createdAt;
    private List<InvoiceItem> invoiceItems;

}

