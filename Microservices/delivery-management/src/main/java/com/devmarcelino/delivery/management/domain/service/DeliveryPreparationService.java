package com.devmarcelino.delivery.management.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.devmarcelino.delivery.management.api.model.ContactPointDto;
import com.devmarcelino.delivery.management.api.model.DeliveryRequest;
import com.devmarcelino.delivery.management.api.model.ItemDto;
import com.devmarcelino.delivery.management.domain.exception.DomainException;
import com.devmarcelino.delivery.management.domain.model.ContactPoint;
import com.devmarcelino.delivery.management.domain.model.Delivery;
import com.devmarcelino.delivery.management.domain.repository.DeliveryRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryPreparationService {
    
    private final DeliveryRepository deliveryRepository;
    private final DeliveryTimeEstimationService deliveryTimeEstimationService;
    private final CourierPayoutCalculationService courierPayoutCalculationService;

    @Transactional
    public Delivery draft(DeliveryRequest request){
        Delivery delivery = Delivery.draft();
        handlePreparation(request, delivery);
        return deliveryRepository.saveAndFlush(delivery);
    }

    @Transactional
    public Delivery edit(UUID deliveryId, @Valid DeliveryRequest request) {
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(
            () -> new DomainException()
        );
        delivery.removeItems();
        handlePreparation(request, delivery);
        return deliveryRepository.saveAndFlush(delivery);
    }

    private void handlePreparation(@Valid DeliveryRequest request, Delivery delivery) {
        ContactPointDto senderDto = request.sender();
        ContactPointDto recipientDto = request.recipient();

        ContactPoint sender = ContactPoint.builder()
        .name(senderDto.name())
        .complement(senderDto.complement())
        .number(senderDto.number())
        .phone(senderDto.phone())
        .street(senderDto.street())
        .zipcode(senderDto.zipcode())
        .build();

        ContactPoint recipient = ContactPoint.builder()
        .name(recipientDto.name())
        .complement(recipientDto.complement())
        .number(recipientDto.number())
        .phone(recipientDto.phone())
        .street(recipientDto.street())
        .zipcode(recipientDto.zipcode())
        .build();

        var estimate = deliveryTimeEstimationService.estimate(sender, recipient);
        BigDecimal payout = courierPayoutCalculationService.calculatePayout(estimate.getDistanceInKm());
        BigDecimal distanceFee = calculateFee(estimate.getDistanceInKm());

        var preparationDetails = Delivery.PreparationDetails.builder()
        .recipient(recipient)
        .sender(sender)
        .expectedDeliveryTime(estimate.getEstimatedTime())
        .courierPayout(payout)
        .distanceFee(distanceFee)
        .build();

        delivery.editPreparationDetails(preparationDetails);

        for(ItemDto item
         : request.items()) {
            delivery.addItem(item.name(),item.quantity());
        }


    }

    private BigDecimal calculateFee(Double distanceInKm) {
        return new BigDecimal("3").multiply(new BigDecimal(distanceInKm))
        .setScale(2, RoundingMode.HALF_EVEN);
    }
}
