package com.devmarcelino.delivery.management.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devmarcelino.delivery.management.domain.model.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, UUID>{
    
}
