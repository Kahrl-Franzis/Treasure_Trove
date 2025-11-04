package com.asis.controller;

import com.asis.dto.OrderRequestDTO;
import com.asis.entity.OrderData;
import com.asis.entity.OrderItemData;
import com.asis.entity.Pirate;
import com.asis.entity.Product;
import com.asis.repository.PirateDataRepository;
import com.asis.repository.OrderDataRepository;
import com.asis.repository.ProductDataRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderDataRepository orderRepository;
    private final PirateDataRepository pirateRepository;
    private final ProductDataRepository productRepository;

    public OrderController(OrderDataRepository orderRepository,
                           PirateDataRepository pirateRepository,
                           ProductDataRepository productRepository) {
        this.orderRepository = orderRepository;
        this.pirateRepository = pirateRepository;
        this.productRepository = productRepository;
    }

    @PostMapping
    public ResponseEntity<OrderData> createOrder(@RequestBody OrderRequestDTO orderRequest) {
        System.out.println("🔥 Received order for pirate username: " + orderRequest.getPirateUsername());

        // ✅ 1. Find Pirate by username (case-insensitive)
        Pirate pirate = pirateRepository.findByUsernameIgnoreCase(orderRequest.getPirateUsername());
        if (pirate == null) {
            throw new RuntimeException("Pirate not found with username: " + orderRequest.getPirateUsername());
        }

        // ✅ 2. Create the Order
        OrderData order = new OrderData();
        order.setPirate(pirate);
        order.setTotalAmount(orderRequest.getTotalAmount());
        order.setStatus(orderRequest.getStatus() != null ? orderRequest.getStatus() : "PENDING_PAYMENT");

        // ✅ 3. Create Order Items
        List<OrderItemData> orderItems = new ArrayList<>();
        for (OrderRequestDTO.OrderItemDTO itemDTO : orderRequest.getOrderItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + itemDTO.getProductId()));

            OrderItemData orderItem = new OrderItemData();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setPriceAtPurchase(itemDTO.getPrice());

            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);

        // ✅ 4. Save the order
        OrderData savedOrder = orderRepository.save(order);
        System.out.println("✅ Order created for pirate: " + pirate.getUsername() + " (Order ID: " + savedOrder.getId() + ")");

        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }
}
