
package com.devmarcelino.delivery.management.domain.service;

import java.time.Duration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeliveryEstimate {
    private Duration estimatedTime;
    private Double distanceInKm;
    
}