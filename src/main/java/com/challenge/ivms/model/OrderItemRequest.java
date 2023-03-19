package com.challenge.ivms.model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {

    private Long userId;
    private List<OrderItemRequestItem> orderItems;

    @Data
    @NoArgsConstructor
    public static class OrderItemRequestItem {
        private String productId;
        private int quantity;
    }

}
