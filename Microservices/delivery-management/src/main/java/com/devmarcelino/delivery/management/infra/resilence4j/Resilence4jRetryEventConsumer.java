package com.devmarcelino.delivery.management.infra.resilence4j;

import org.springframework.stereotype.Component;

import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import io.github.resilience4j.retry.Retry;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Resilence4jRetryEventConsumer implements RegistryEventConsumer<Retry>{
    
    @Override
    public void onEntryAddedEvent(EntryAddedEvent<Retry> entryAddedEvent) {
        entryAddedEvent.getAddedEntry().getEventPublisher()
        .onEvent(
            event -> log.info(event.toString())
        );
    }

    @Override
    public void onEntryRemovedEvent(EntryRemovedEvent<Retry> entryRemoveEvent) {
         entryRemoveEvent.getRemovedEntry().getEventPublisher()
        .onEvent(
            event -> log.info(event.toString())
        );
    }

    @Override
    public void onEntryReplacedEvent(EntryReplacedEvent<Retry> entryReplacedEvent) {
         entryReplacedEvent.getNewEntry().getEventPublisher()
        .onEvent(
            event -> log.info(event.toString())
        );
    }
    
}
