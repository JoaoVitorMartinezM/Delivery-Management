package com.devmarcelino.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import io.github.resilience4j.retry.Retry;

@Component
public class Resilence4jRetryEventConsumer implements RegistryEventConsumer<Retry>{

    private static final Logger log = LoggerFactory.getLogger(Resilence4jRetryEventConsumer.class);
    
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
