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
        final List<OrderItem> orderItems = order.getOrderItems();
        double totalAmount = 0.0;

        final List<InvoiceItem> invoiceItems = new ArrayList<>();

        for (final OrderItem orderItem : orderItems) {
            final Product product = productService.getProductById(orderItem.getItemId());
            final double itemPrice = product.getPrice();
            final double itemTotal = itemPrice * orderItem.getQuantity();
            totalAmount += itemTotal;

            final InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setProductName(product.getName());
            invoiceItem.setQuantity(orderItem.getQuantity());
            invoiceItem.setPrice(itemPrice);
            invoiceItem.setTotal(itemTotal);

            invoiceItems.add(invoiceItem);
        }

        final Invoice invoice = new Invoice();
        invoice.setOrderId(order.getId());
        invoice.setCustomerName(order.getCustomerName());
        invoice.setAddress(order.getAddress());
        invoice.setTotalAmount(totalAmount);
        invoice.setInvoiceItems(invoiceItems);
        invoice.setCreatedAt(LocalDateTime.now());

        return invoice;
    }

}

