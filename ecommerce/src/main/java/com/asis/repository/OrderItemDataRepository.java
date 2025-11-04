package com.asis.repository;

import com.asis.entity.OrderItemData;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderItemDataRepository extends JpaRepository<OrderItemData, Long> {
    // FIX: Add the custom methods the service is trying to call.
    // Use Long for the ID type and find by the Pirate ID linked through OrderData
    // Note: The actual query depends on your data model, but this is the simplest JPA solution.
    List<OrderItemData> findAllByOrder_Pirate_Id(Long pirateId);
}