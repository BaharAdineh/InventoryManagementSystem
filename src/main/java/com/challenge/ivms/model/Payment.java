package com.challenge.ivms.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Payment {
    private Long id;
    private String cardHolderName;
    private String cardNumber;
    private String cvv;
    private LocalDate expirationDate;
}

