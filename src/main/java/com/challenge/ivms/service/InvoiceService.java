package com.challenge.ivms.service;

@Service
public class InvoiceService {

    public Invoice generateInvoice(Order order) {
        List<OrderItem> orderItems = order.getOrderItems();
        double totalAmount = 0.0;

        List<InvoiceItem> invoiceItems = new ArrayList<>();

        for (OrderItem orderItem : orderItems) {
            Product product = productService.getProductById(orderItem.getProductId());
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

