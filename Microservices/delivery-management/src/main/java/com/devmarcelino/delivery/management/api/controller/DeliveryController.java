package com.devmarcelino.delivery.management.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.devmarcelino.delivery.management.api.model.CourierIdRequest;
import com.devmarcelino.delivery.management.api.model.DeliveryRequest;
import com.devmarcelino.delivery.management.domain.model.Delivery;
import com.devmarcelino.delivery.management.domain.repository.DeliveryRepository;
import com.devmarcelino.delivery.management.domain.service.DeliveryCheckpointService;
import com.devmarcelino.delivery.management.domain.service.DeliveryPreparationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryRepository deliveryRepository;

    private final DeliveryPreparationService deliveryPreparationService;
    private final DeliveryCheckpointService deliveryCheckpointService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Delivery draft(@RequestBody @Valid DeliveryRequest request) {
        
        return deliveryPreparationService.draft(request);
    }

    @PutMapping("/{deliveryId}")
    public Delivery edit(@PathVariable UUID deliveryId, @RequestBody @Valid DeliveryRequest request) {
        
        return deliveryPreparationService.edit(deliveryId, request);
    }

    @GetMapping
    public PagedModel<Delivery> findAll(@PageableDefault Pageable pageable) {
        return new PagedModel<>(deliveryRepository.findAll(pageable));
    }

    @GetMapping("/{deliveryId}")
    public Delivery findById(@PathVariable UUID deliveryId) {
        return deliveryRepository.findById(deliveryId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/{deliveryId}/placement")
    public void place(@PathVariable UUID deliveryId) {
        deliveryCheckpointService.place(deliveryId);
    }
    
    @PostMapping("/{deliveryId}/pickups")
    public void pickUp(@PathVariable UUID deliveryId, @Valid @RequestBody CourierIdRequest CourierIdRequest) {
        deliveryCheckpointService.pickUp(deliveryId, CourierIdRequest.getId());
    }

    @PostMapping("/{deliveryId}/completion")
    public void complete(@PathVariable UUID deliveryId) {
        deliveryCheckpointService.complete(deliveryId);
        
    }

    
    
    
    
}
