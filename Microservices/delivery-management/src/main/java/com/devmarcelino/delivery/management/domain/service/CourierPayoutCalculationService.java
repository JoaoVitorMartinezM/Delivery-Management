package com.devmarcelino.delivery.management.domain.service;

import java.math.BigDecimal;


public interface CourierPayoutCalculationService {
    BigDecimal calculatePayout(Double distanceInKm);
        
}
