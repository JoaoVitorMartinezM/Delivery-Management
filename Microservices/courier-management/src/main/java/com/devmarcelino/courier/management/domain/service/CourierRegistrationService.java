package com.devmarcelino.courier.management.domain.service;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;

import com.devmarcelino.courier.management.api.model.CourierRequest;
import com.devmarcelino.courier.management.domain.model.Courier;
import com.devmarcelino.courier.management.domain.repository.CourierRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CourierRegistrationService {

    private final CourierRepository courierRepository;

    public Courier create(@Valid CourierRequest request) {
        Courier courier = Courier.brandNew(request.getName(), request.getPhone());
        return courierRepository.saveAndFlush(courier); 
    }

    public Courier update(UUID courierId, @Valid CourierRequest request) {
        Courier courier = courierRepository.findById(courierId).orElseThrow(
        );
        courier.setName(request.getName());
        courier.setPhone(request.getPhone());

        return courierRepository.saveAndFlush(courier); 
    }

    
}
