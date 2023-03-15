package com.challenge.ivms.repository;

import com.challenge.ivms.model.PurchaseOrder;
import com.challenge.ivms.model.PurchaseOrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderRepository extends MongoRepository<PurchaseOrder, String> {
    List<PurchaseOrder> findByVendor(String vendor);

    List<PurchaseOrder> findByStatus(PurchaseOrderStatus status);
}
