package com.asis.serviceimpl;

import com.asis.entity.OrderData;
import com.asis.entity.OrderItemData;
import com.asis.entity.Pirate; // Assuming Pirate is the customer entity for lookups
import com.asis.service.OrderService;
import com.asis.service.ProductService; // Dependency: Your ProductServiceImpl
import com.asis.repository.OrderDataRepository;
import org.springframework.stereotype.Service; // CRITICAL: Registers the bean
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service // <--- THIS MUST BE PRESENT AND CORRECTLY IMPORTED
public class OrderServiceImpl implements OrderService {

    private final OrderDataRepository orderRepository;
    private final ProductService productService; // Dependency

    // Constructor Injection (Spring finds and injects the beans)
    public OrderServiceImpl(OrderDataRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    // --- Core Transactional Workflow ---

    @Override
    @Transactional
    public OrderData create(OrderData order) {
        // 1. DEDUCT STOCK using the injected ProductService
        for (OrderItemData item : order.getOrderItems()) {
            int orderedQuantity = item.getQuantity();
            productService.updateStock(item.getProduct().getId(), -orderedQuantity);
        }

        // 2. SAVE ORDER
        order.setStatus("CREATED");
        return orderRepository.save(order);
    }

    @Override
    public Optional<OrderData> get(Long id) {
        return orderRepository.findById(id);
    }

    // --- Remaining Required Methods (Implemented to satisfy the interface) ---

    @Override
    public OrderData pay(OrderData order) {
        order.setStatus("PAID");
        return orderRepository.save(order);
    }

    @Override
    public OrderData ship(OrderData order) {
        order.setStatus("SHIPPED");
        return orderRepository.save(order);
    }

    @Override
    public OrderData cancel(OrderData order) {
        order.setStatus("CANCELLED");
        // Logic to restock items would go here
        return orderRepository.save(order);
    }

    @Override
    public List<OrderData> getOrdersByPirate(Pirate pirate) {
        // Requires findByPirate method in OrderDataRepository
        return orderRepository.findByPirate(pirate);
    }

    // Placeholders for all other interface methods:
    @Override public OrderData invoice(OrderData order) { return orderRepository.save(order); }
    @Override public OrderData pick(OrderData order) { order.setStatus("IN_FULFILLMENT"); return orderRepository.save(order); }
    @Override public OrderData complete(OrderData order) { order.setStatus("COMPLETED"); return orderRepository.save(order); }
    @Override public OrderData suspend(OrderData order) { order.setStatus("ON_HOLD"); return orderRepository.save(order); }
    @Override public OrderData update(OrderData order) { return orderRepository.save(order); }
}