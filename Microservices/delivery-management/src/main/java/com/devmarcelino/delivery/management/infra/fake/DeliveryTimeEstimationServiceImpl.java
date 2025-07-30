package com.devmarcelino.delivery.management.infra.fake;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.devmarcelino.delivery.management.domain.model.ContactPoint;
import com.devmarcelino.delivery.management.domain.service.DeliveryEstimate;
import com.devmarcelino.delivery.management.domain.service.DeliveryTimeEstimationService;

@Service
public class DeliveryTimeEstimationServiceImpl implements DeliveryTimeEstimationService{

    @Override
    public DeliveryEstimate estimate(ContactPoint sender, ContactPoint recipient) {
        return new DeliveryEstimate(Duration.ofHours(3) , 3.1);
    }

    
}