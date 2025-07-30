package com.devmarcelino.delivery.management.infra.http.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CourierPayoutCalculationRequest {

    private Double distanceInKm;
}
