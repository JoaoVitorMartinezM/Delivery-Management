package com.devmarcelino.delivery.management.domain.event;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class DeliveryPlacedEvent {
    private final OffsetDateTime occurredAt;
    private final UUID deliveryId;
}
