package com.devmarcelino.delivery.management.api.model;

import java.util.List;

public record DeliveryRequest( ContactPointDto sender, ContactPointDto recipient, List<ItemDto> items) {
    
}
