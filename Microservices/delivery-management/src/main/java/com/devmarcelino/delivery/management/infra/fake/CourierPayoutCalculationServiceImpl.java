package com.devmarcelino.delivery.management.infra.fake;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.devmarcelino.delivery.management.domain.service.CourierPayoutCalculationService;

public class CourierPayoutCalculationServiceImpl implements CourierPayoutCalculationService{

    @Override
    public BigDecimal calculatePayout(Double distanceInKm) {
        return new BigDecimal("5").multiply(new BigDecimal(distanceInKm))
        .setScale(2, RoundingMode.HALF_EVEN);
    }
    
}
