package com.devmarcelino.courier.management.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devmarcelino.courier.management.domain.model.Courier;

@Repository
public interface CourierRepository extends JpaRepository<Courier, UUID>{

    Optional<Courier> findTop1ByOrderByLastFulfilledDeliveryAtAsc();

    Optional<Courier> findByPendingDeliveries_id(UUID deliveryId);
    
}
