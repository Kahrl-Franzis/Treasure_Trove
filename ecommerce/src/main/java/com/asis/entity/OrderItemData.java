package com.asis.entity;

import javax.persistence.*;
import java.math.BigDecimal; // Crucial for accurate financial calculations

@Entity
@Table(name = "order_items")
public class OrderItemData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Links this item to the parent Order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderData order;

    // Links to the Product (Treasure) details at the time of order
    // NOTE: This assumes your Product entity is named 'Product'
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    // Using BigDecimal for financial accuracy (10 total digits, 2 decimal places)
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal priceAtPurchase;

    // JPA requires an empty constructor
    public OrderItemData() {
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderData getOrder() {
        return order;
    }

    public void setOrder(OrderData order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(BigDecimal priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }

    // Helper method to calculate the item subtotal (optional, but useful)
    public BigDecimal calculateSubtotal() {
        if (priceAtPurchase == null) return BigDecimal.ZERO;
        return priceAtPurchase.multiply(BigDecimal.valueOf(quantity));
    }
}