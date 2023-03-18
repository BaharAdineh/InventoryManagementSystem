package com.challenge.ivms.service;
import com.challenge.ivms.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    private ProductService productService;

    public Invoice generateInvoice(Order order) {
        List<OrderItem> orderItems = order.getOrderItems();
        double totalAmount = 0.0;

        List<InvoiceItem> invoiceItems = new ArrayList<>();

        for (OrderItem orderItem : orderItems) {
            Product product = productService.getProductById(orderItem.getItemId());
            double itemPrice = product.getPrice();
            double itemTotal = itemPrice * orderItem.getQuantity();
            totalAmount += itemTotal;

            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setProductName(product.getName());
            invoiceItem.setQuantity(orderItem.getQuantity());
            invoiceItem.setPrice(itemPrice);
            invoiceItem.setTotal(itemTotal);

            invoiceItems.add(invoiceItem);
        }

        Invoice invoice = new Invoice();
        invoice.setOrderId(order.getId());
        invoice.setCustomerName(order.getCustomerName());
        invoice.setAddress(order.getAddress());
        invoice.setTotalAmount(totalAmount);
        invoice.setInvoiceItems(invoiceItems);
        invoice.setCreatedAt(LocalDateTime.now());

        return invoice;
    }

}

