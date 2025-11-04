// src/main/java/com/asis/repository/PirateDataRepository.java

package com.asis.repository;

import com.asis.entity.Pirate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PirateDataRepository extends JpaRepository<Pirate, Long> {

    // CRITICAL FINAL FIX: Use IgnoreCase to handle case-sensitive database issues.
    // This method tells Spring to find the user regardless of capitalization.
    Pirate findByUsernameIgnoreCase(String username);

    // Pirate findByEmail(String email); // Removed/commented out
}