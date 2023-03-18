package com.challenge.ivms.model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {

    @NotNull
    private String productId;

    @NotNull
    @Min(1)
    private Integer quantity;


}
