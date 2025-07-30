package com.devmarcelino.delivery.management.infra.event;


public interface IntegrationEventPublisher {

    void publish(Object event, String key, String topic);
}
