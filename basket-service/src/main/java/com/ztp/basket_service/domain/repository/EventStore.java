package com.ztp.basket_service.domain.repository;

import com.ztp.basket_service.domain.event.Event;
import java.util.List;
import java.util.Set;

public interface EventStore {
    void store(Event event);
    List<Event> getEvents(String aggregateId);
    int getLatestVersion(String aggregateId);
    Set<String> getAllAggregateIds();
}
