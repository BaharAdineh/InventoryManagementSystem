package com.challenge.ivms.orderservice.orderCreation;

package com.challenge.ivms.orderservice.service;

import com.challenge.ivms.orderservice.model.Order;
import com.challenge.ivms.orderservice.repository.OrderRepository;
import com.challenge.ivms.orderservice.service.UserService;
import com.challenge.ivms.orderservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Order Creation: Implement an endpoint to create new orders.
//This could involve receiving customer information, selected products, and quantities.
@Service
public class OrderServiceImpl {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    public Order createOrder(String productId, String userId) {
        // Retrieve the user and product information
        Optional<User> user = userService.getUserById(userId);
        Optional<Product> product = productService.getProductById(productId);

        if (user.isPresent() && product.isPresent()) {
            Order order = new Order();
            order.setProduct(product.get());
            order.setUser(user.get());
            // Set other order properties as needed (e.g., quantities)

            return orderRepository.save(order);
        } else {
            // Handle case when user or product is not found
            throw new IllegalArgumentException("Invalid user or product");
        }
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }

    // Additional methods for order management as needed
}