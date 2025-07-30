package com.devmarcelino.delivery.management.infra.resilence4j;

import org.springframework.stereotype.Component;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Resilence4jCircuitBreackerEventConsumer implements RegistryEventConsumer<CircuitBreaker>{
    
    @Override
    public void onEntryAddedEvent(EntryAddedEvent<CircuitBreaker> entryAddedEvent) {
        entryAddedEvent.getAddedEntry().getEventPublisher()
        .onEvent(
            event -> log.info(event.toString())
        );
    }

    @Override
    public void onEntryRemovedEvent(EntryRemovedEvent<CircuitBreaker> entryRemoveEvent) {
         entryRemoveEvent.getRemovedEntry().getEventPublisher()
        .onEvent(
            event -> log.info(event.toString())
        );
    }

    @Override
    public void onEntryReplacedEvent(EntryReplacedEvent<CircuitBreaker> entryReplacedEvent) {
         entryReplacedEvent.getNewEntry().getEventPublisher()
        .onEvent(
            event -> log.info(event.toString())
        );
    }
    
}
