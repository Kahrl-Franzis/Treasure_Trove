package com.asis.repository;

import com.asis.entity.OrderData;
import com.asis.entity.Pirate; // CRITICAL: Import the Pirate entity
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderDataRepository extends JpaRepository<OrderData, Long> {

    /**
     * Finds all OrderData entities associated with a specific Pirate (customer).
     * Spring Data JPA automatically derives the query from the method name.
     */
    List<OrderData> findByPirate(Pirate pirate);
}