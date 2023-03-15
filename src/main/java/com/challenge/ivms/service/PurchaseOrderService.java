package com.challenge.ivms.service;

import com.challenge.ivms.errorHandling.ResourceNotFoundException;
import com.challenge.ivms.model.Item;
import com.challenge.ivms.model.PurchaseOrder;
import com.challenge.ivms.model.PurchaseOrderStatus;
import com.challenge.ivms.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PurchaseOrderService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder) {
        purchaseOrder.setStatus(PurchaseOrderStatus.NEW);
        purchaseOrder.setCreationDate(new Date());
        purchaseOrder.setUpdateDate(new Date());
        return purchaseOrderRepository.save(purchaseOrder);
    }

    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    public PurchaseOrder getPurchaseOrderById(String id) {
        return purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase order not found with id " + id));
    }

    public PurchaseOrder updatePurchaseOrderStatus(String id, PurchaseOrderStatus status) {
        PurchaseOrder purchaseOrder = getPurchaseOrderById(id);
        purchaseOrder.setStatus(status);
        purchaseOrder.setUpdateDate(new Date());
        return purchaseOrderRepository.save(purchaseOrder);
    }

    public PurchaseOrder addItemsToPurchaseOrder(String id, List<Item> items) {
        PurchaseOrder purchaseOrder = getPurchaseOrderById(id);
        List<Item> currentItems = purchaseOrder.getItems();
        currentItems.addAll(items);
        purchaseOrder.setItems(currentItems);
        purchaseOrder.setUpdateDate(new Date());
        return purchaseOrderRepository.save(purchaseOrder);
    }

    public List<PurchaseOrder> getPurchaseOrdersByVendor(String vendor) {
        return purchaseOrderRepository.findByVendor(vendor);
    }

    public List<PurchaseOrder> getPurchaseOrdersByStatus(PurchaseOrderStatus status) {
        return purchaseOrderRepository.findByStatus(status);
    }
}

