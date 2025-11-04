package com.asis.serviceimpl;

import com.asis.entity.OrderItemData;
import com.asis.model.OrderItem;
import com.asis.enums.OrderItemStatus;
import com.asis.service.OrderItemService;
import com.asis.repository.OrderItemDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemDataRepository orderItemDataRepository;

    public OrderItemServiceImpl(OrderItemDataRepository orderItemDataRepository) {
        this.orderItemDataRepository = orderItemDataRepository;
    }

    // --- Mapper Methods (DEFINITIVE FIX based on primitive model) ---

    // NOTE: We assume OrderItemData uses Long for ID and int for Quantity
    // and BigDecimal for priceAtPurchase.

    private OrderItem convertToModel(OrderItemData entity) {
        if (entity == null) return null;

        OrderItem model = new OrderItem();
        // FIX 1: Convert Long ID from entity to int ID for model (potential data loss if ID > 2.1B)
        model.setId(entity.getId().intValue());

        // FIX 2: Convert int Quantity from entity to double Quantity for model (as per your model)
        model.setQuantity((double) entity.getQuantity());

        // You would also map price: model.setPrice(entity.getPriceAtPurchase().doubleValue());

        return model;
    }

    private OrderItemData convertToEntity(OrderItem model) {
        if (model == null) return null;

        OrderItemData entity = new OrderItemData();

        // FIX 3: Convert int ID from model to Long ID for entity
        if (model.getId() != 0) { // Check against 0, as int cannot be null
            entity.setId((long) model.getId());
        }

        // FIX 4: Convert double Quantity from model to int Quantity for entity
        entity.setQuantity((int) model.getQuantity());

        // You would also map price: entity.setPriceAtPurchase(BigDecimal.valueOf(model.getPrice()));

        return entity;
    }

    // --- Implementation Methods (Now using the fixed converters) ---

    @Override
    public List<OrderItem> getAll() {
        return orderItemDataRepository.findAll().stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderItem> getOrderItems(Integer customerId) {
        // Placeholder - requires complex repository logic
        return List.of();
    }

    @Override
    public List<OrderItem> getCartItems(Integer customerId) {
        // Placeholder - requires cart repository/logic
        return List.of();
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
        OrderItemData savedEntity = orderItemDataRepository.save(convertToEntity(orderItem));
        return convertToModel(savedEntity);
    }

    @Override
    @Transactional
    public List<OrderItem> create(List<OrderItem> orderItems) {
        List<OrderItemData> entities = orderItems.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());

        return orderItemDataRepository.saveAll(entities).stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        OrderItemData savedEntity = orderItemDataRepository.save(convertToEntity(orderItem));
        return convertToModel(savedEntity);
    }

    @Override
    @Transactional
    public List<OrderItem> update(List<OrderItem> orderItems) {
        List<OrderItemData> entities = orderItems.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());

        return orderItemDataRepository.saveAll(entities).stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<OrderItem> updateStatus(List<Integer> ids, OrderItemStatus orderItemStatus) {
        List<Long> longIds = ids.stream().map(Integer::longValue).collect(Collectors.toList());
        List<OrderItemData> itemsToUpdate = orderItemDataRepository.findAllById(longIds);

        // Logic to set status on the OrderItemData entity

        return orderItemDataRepository.saveAll(itemsToUpdate).stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItem get(Integer id) {
        Long longId = id.longValue();
        Optional<OrderItemData> entity = orderItemDataRepository.findById(longId);

        return entity.map(this::convertToModel)
                .orElseThrow(() -> new RuntimeException("OrderItem not found with ID: " + id));
    }

    @Override
    public void delete (Integer id) {
        orderItemDataRepository.deleteById(id.longValue());
    }
}