package com.asis.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Correctly linked to the Pirate entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pirate_id", nullable = false)
    private Pirate pirate;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    private String status = "PENDING_PAYMENT"; // E.g., PENDING_PAYMENT, PAID, SHIPPED, COMPLETED

    private Instant orderDate = Instant.now();

    // A one-to-many relationship with OrderItemData
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemData> orderItems;

    // ... other fields (e.g., shippingAddress, paymentId)

    public OrderData() {}

    // Getters and Setters (omitted for brevity, assume they exist)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Pirate getPirate() { return pirate; }
    public void setPirate(Pirate pirate) { this.pirate = pirate; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<OrderItemData> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItemData> orderItems) { this.orderItems = orderItems; }
}