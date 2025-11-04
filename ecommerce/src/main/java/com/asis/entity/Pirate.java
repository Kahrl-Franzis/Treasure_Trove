package com.asis.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "pirates")
public class Pirate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // CRITICAL FIX 1: Enforce that username is required and unique
    @Column(nullable = false, unique = true)
    private String username;

    // CRITICAL FIX 2: Enforce that email is required and unique
    @Column(nullable = false, unique = true)
    private String email;

    // CRITICAL FIX 3: Enforce that passwordHash is required
    @Column(nullable = false)
    private String passwordHash;

    // CRITICAL FIX 4: Default shipping address is required for any order
    @Column(nullable = false)
    private String defaultShippingAddress;

    private Instant joinedCrewAt = Instant.now();

    @OneToOne(mappedBy = "pirate", cascade = CascadeType.ALL)
    private Cart treasureChest;

    @OneToMany(mappedBy = "pirate")
    private List<OrderData> orders;

    public Pirate() {}

    // --- FULL GETTERS & SETTERS ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getDefaultShippingAddress() {
        return defaultShippingAddress;
    }

    public void setDefaultShippingAddress(String defaultShippingAddress) {
        this.defaultShippingAddress = defaultShippingAddress;
    }

    public Instant getJoinedCrewAt() {
        return joinedCrewAt;
    }

    public void setJoinedCrewAt(Instant joinedCrewAt) {
        this.joinedCrewAt = joinedCrewAt;
    }

    public Cart getTreasureChest() {
        return treasureChest;
    }

    public void setTreasureChest(Cart treasureChest) {
        this.treasureChest = treasureChest;
    }

    public List<OrderData> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderData> orders) {
        this.orders = orders;
    }
}