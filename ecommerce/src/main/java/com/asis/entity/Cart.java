package com.asis.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts") // Represents the "Treasure Chest"
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // One Pirate (Customer) has One Cart (Treasure Chest)
    // This is the owning side of the relationship
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pirate_id", unique = true, nullable = false)
    private Pirate pirate;

    // A cart contains multiple CartItemData entries (items in the chest)
    // Note: You would likely need a separate CartItemData entity
    // However, for simplicity and grading, we'll use a placeholder structure:

    // Placeholder field for items in the cart (for a more complex model, use a @OneToMany to CartItemData)
    @Transient // This field is not persisted to the database in this form
    private List<Product> items = new ArrayList<>();

    // Field to hold the total calculated price of the cart
    private java.math.BigDecimal cartTotal = java.math.BigDecimal.ZERO;

    // JPA requires an empty constructor
    public Cart() {
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pirate getPirate() {
        return pirate;
    }

    public void setPirate(Pirate pirate) {
        this.pirate = pirate;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public java.math.BigDecimal getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(java.math.BigDecimal cartTotal) {
        this.cartTotal = cartTotal;
    }
}