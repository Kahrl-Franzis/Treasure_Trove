package com.asis.service;

import com.asis.entity.OrderData; // Assuming OrderData is your main order entity
import com.asis.entity.Pirate;   // Assuming Pirate is your customer entity
import java.util.List;
import java.util.Optional;

// NOTE: This interface uses the OrderData entity to represent the state in the business layer.
public interface OrderService {

    // --- Core Transactional Workflow ---

    /**
     * Initiates the checkout process: saves the order, sets initial status,
     * and triggers stock deduction via the service implementation.
     */
    OrderData create(OrderData order);

    /**
     * Handles payment processing and updates order status.
     * This is where payment gateway API calls would be handled in the implementation.
     */
    OrderData pay(OrderData order);

    /**
     * Handles order fulfillment logic (e.g., checking items, packaging).
     */
    OrderData pick(OrderData order);

    /**
     * Handles shipping integration (e.g., real-time rate calculation, generating tracking number).
     */
    OrderData ship(OrderData order);

    /**
     * Finalizes the order, typically after confirmation of delivery.
     */
    OrderData complete(OrderData order);

    /**
     * Marks the order as cancelled.
     */
    OrderData cancel(OrderData order);

    /**
     * Puts the order on hold (e.g., pending address verification).
     */
    OrderData suspend(OrderData order);

    // --- CRUD/Utility Methods ---

    /**
     * Finds an order by its ID (using Long, consistent with entity IDs).
     */
    Optional<OrderData> get(Long id);

    /**
     * Finds all orders belonging to a specific Pirate (customer).
     */
    List<OrderData> getOrdersByPirate(Pirate pirate);

    /**
     * Generates a final invoice (often done during or after payment).
     */
    OrderData invoice(OrderData order);

    /**
     * Updates order details (e.g., shipping address change before shipping).
     */
    OrderData update(OrderData order);
}