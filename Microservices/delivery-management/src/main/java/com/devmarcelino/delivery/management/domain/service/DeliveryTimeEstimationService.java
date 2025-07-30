package com.devmarcelino.delivery.management.domain.service;

import com.devmarcelino.delivery.management.domain.model.ContactPoint;

public interface DeliveryTimeEstimationService {
    DeliveryEstimate estimate(ContactPoint sender, ContactPoint recipient);
}
