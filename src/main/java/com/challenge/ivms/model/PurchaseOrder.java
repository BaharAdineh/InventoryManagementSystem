package com.challenge.ivms.model;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("purchaseOrders")
public class PurchaseOrder {
    @Id
    private String id;
    private String vendor;
    private List<Item> items;
    private PurchaseOrderStatus status;
    private Date creationDate;
    private Date updateDate;
}

