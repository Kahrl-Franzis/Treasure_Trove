package com.asis.repository;

import com.asis.entity.Product; // Corresponds to your Product entity
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDataRepository extends JpaRepository<Product, Long> {
    // JpaRepository provides: save, findById, findAll, delete, etc.
}