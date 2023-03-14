package com.challenge.ivms.service;

import com.challenge.ivms.model.Order;
import com.challenge.ivms.model.OrderItem;
import com.challenge.ivms.model.Product;
import com.challenge.ivms.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private InvoiceService invoiceService;

    public void processOrder(Order order) {
        List<OrderItem> orderItems = order.getOrderItems();

        for (OrderItem orderItem : orderItems) {
            Product product = productService.getProductById(orderItem.getProductId());
            int availableQuantity = product.getQuantity();

            if (availableQuantity >= orderItem.getQuantity()) {
                int updatedQuantity = availableQuantity - orderItem.getQuantity();
                product.setQuantity(updatedQuantity);
                productService.updateProduct(product.getId(), product);
            } else {
                // Notify the customer and suggest alternative products
            }
        }

        Invoice invoice = invoiceService.generateInvoice(order);
        // Send the invoice to the customer
    }

}

