package com.asis.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderRequestDTO {
    private String pirateUsername;  // ✅ Changed from Long pirateId → String pirateUsername
    private BigDecimal totalAmount;
    private String status;
    private List<OrderItemDTO> orderItems;

    // Required empty constructor for Jackson
    public OrderRequestDTO() {}

    // ✅ Getters and Setters
    public String getPirateUsername() {
        return pirateUsername;
    }

    public void setPirateUsername(String pirateUsername) {
        this.pirateUsername = pirateUsername;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public static class OrderItemDTO {
        private Long productId;
        private Integer quantity;
        private BigDecimal price;

        // Required empty constructor
        public OrderItemDTO() {}

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }
}
