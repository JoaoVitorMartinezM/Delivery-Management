package com.devmarcelino.delivery.management.infra.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.devmarcelino.delivery.management.domain.event.DeliveryFulfilledEvent;
import com.devmarcelino.delivery.management.domain.event.DeliveryPickUpEvent;
import com.devmarcelino.delivery.management.domain.event.DeliveryPlacedEvent;
import com.devmarcelino.delivery.management.infra.kafka.KafkaTopicConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
@RequiredArgsConstructor
public class DeliveryDomainEventHandler {

    private final IntegrationEventPublisher integrationEventPublisher;

    @EventListener
    public void handle(DeliveryPlacedEvent event){
        log.info(event.toString());
        integrationEventPublisher.publish(event, event.getDeliveryId().toString(), KafkaTopicConfig.deliveryEventsTopicName);

    }

     @EventListener
    public void handle(DeliveryPickUpEvent event){
        log.info(event.toString());
        integrationEventPublisher.publish(event, event.getDeliveryId().toString(), KafkaTopicConfig.deliveryEventsTopicName);

    }

     @EventListener
    public void handle(DeliveryFulfilledEvent event){
        log.info(event.toString());
        integrationEventPublisher.publish(event, event.getDeliveryId().toString(), KafkaTopicConfig.deliveryEventsTopicName);
    }

}
