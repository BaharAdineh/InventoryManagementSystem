package com.challenge.ivms.controller;

import com.challenge.ivms.model.PurchaseOrder;
import com.challenge.ivms.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @PostMapping("")
    public ResponseEntity<PurchaseOrder> createPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
        final PurchaseOrder savedPurchaseOrder = purchaseOrderService.savePurchaseOrder(purchaseOrder);
        return new ResponseEntity<>(savedPurchaseOrder, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<PurchaseOrder>> getAllPurchaseOrders() {
        final List<PurchaseOrder> purchaseOrders = purchaseOrderService.getAllPurchaseOrders();
        return new ResponseEntity<>(purchaseOrders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> getPurchaseOrderById(@PathVariable String id) {
        final PurchaseOrder purchaseOrder = purchaseOrderService.getPurchaseOrderById(id);
        return new ResponseEntity<>(purchaseOrder, HttpStatus.OK);
    }
}

