package com.devmarcelino.delivery.management.infra.kafka;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.devmarcelino.delivery.management.infra.event.IntegrationEventPublisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class IntegrationEventPublisherKaftaImpl implements IntegrationEventPublisher{

    private final KafkaTemplate<String, Object> kafkaTemplate;
    
    @Override
    public void publish(Object event, String key, String topic) {
        SendResult<String,Object> result = kafkaTemplate.send(topic, key, event).join();
        RecordMetadata recordMetadata = result.getRecordMetadata();
        log.info("Message publish: \n\t Topic: {} \n\t Offset: {}", recordMetadata.topic(), recordMetadata.offset());
    }
    
}
