package com.asis.repository;

import com.asis.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    // Custom query to find a cart by the Pirate's ID
    // Optional<Cart> findByPirateId(Long pirateId);
}