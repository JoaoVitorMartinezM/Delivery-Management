package com.devmarcelino.courier.management.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.devmarcelino.courier.management.api.model.CourierPayoutCalculationRequest;
import com.devmarcelino.courier.management.api.model.CourierPayoutResultModel;
import com.devmarcelino.courier.management.api.model.CourierRequest;
import com.devmarcelino.courier.management.domain.model.Courier;
import com.devmarcelino.courier.management.domain.repository.CourierRepository;
import com.devmarcelino.courier.management.domain.service.CourierPayoutService;
import com.devmarcelino.courier.management.domain.service.CourierRegistrationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/v1/couriers")
@RequiredArgsConstructor
public class CourierController {

    private final CourierRegistrationService courierRegistrationService;
    private final CourierRepository courierRepository;
    private final CourierPayoutService courierPayoutService;


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Courier create(@Valid @RequestBody CourierRequest request) {
        
        return courierRegistrationService.create(request);
    }

    @PutMapping("/{courierId}")
    public Courier update(@PathVariable UUID courierId, @Valid @RequestBody CourierRequest request) {
        return courierRegistrationService.update(courierId, request);
    }

    @GetMapping
    public PagedModel<Courier> findAll(@PageableDefault Pageable pageable) {
        return new PagedModel<>(courierRepository.findAll(pageable));
    }

    @GetMapping("/{courierId}")
    public Courier findbyId(@PathVariable UUID courierId) {
        return courierRepository.findById(courierId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping("/payout-calculation")
    public CourierPayoutResultModel calculate(@RequestBody CourierPayoutCalculationRequest request) {
        BigDecimal payoutFee = courierPayoutService.calculate(request.getDistanceInKm());
        return new CourierPayoutResultModel(payoutFee);
    }
    
}
