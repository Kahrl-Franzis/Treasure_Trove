package com.asis.repository;

import com.asis.entity.Category; // <-- ADD THIS IMPORT

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}