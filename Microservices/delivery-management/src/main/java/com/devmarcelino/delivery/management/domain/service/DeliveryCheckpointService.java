package com.devmarcelino.delivery.management.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devmarcelino.delivery.management.domain.exception.DomainException;
import com.devmarcelino.delivery.management.domain.model.Delivery;
import com.devmarcelino.delivery.management.domain.repository.DeliveryRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class DeliveryCheckpointService {

    private final DeliveryRepository deliveryRepository;

    public void place(UUID deliveryId){
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(
            () -> new DomainException()
        );

        delivery.place();
        deliveryRepository.saveAndFlush(delivery);
        
    }

    public void pickUp(UUID deliveryId, UUID courierId){
                Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(
            () -> new DomainException()
        );

        delivery.pickUp(courierId);
        deliveryRepository.saveAndFlush(delivery);
        
    }

    public void complete(UUID deliveryId){
                Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(
            () -> new DomainException()
        );

        delivery.markAsDelivered();
        deliveryRepository.saveAndFlush(delivery);
        
    }
    
}
