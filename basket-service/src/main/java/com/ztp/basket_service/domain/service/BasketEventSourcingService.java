package com.ztp.basket_service.domain.service;

import com.ztp.basket_service.domain.event.*;
import com.ztp.basket_service.domain.model.Basket;
import com.ztp.basket_service.domain.model.BasketItem;
import com.ztp.basket_service.domain.model.BasketStatus;
import com.ztp.basket_service.domain.repository.EventStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketEventSourcingService {
    private final EventStore eventStore;

    public void appendEvent(BasketEvent event) {
        eventStore.store(event);
    }

    public List<Basket> findOpenBaskets() {
        return eventStore.getAllAggregateIds()
                .stream()
                .map(this::reconstructBasket)
                .filter(basket -> basket.getStatus() == BasketStatus.OPEN)
                .toList();
    }

    public Basket reconstructBasket(String basketId) {
        List<Event> events = eventStore.getEvents(basketId);
        if (events.isEmpty()) {
            throw new IllegalArgumentException("Basket not found with id: " + basketId);
        }

        BasketEvent firstEvent = validateAndCastFirstEvent(events.get(0));
        Basket basket = initializeBasket(basketId, firstEvent);

        events.stream()
                .filter(BasketEvent.class::isInstance)
                .map(BasketEvent.class::cast)
                .forEach(event -> applyEvent(basket, event));

        return basket;
    }

    public BasketEvent validateAndCastFirstEvent(Event event) {
        if(!(event instanceof BasketEvent)) {
            throw new IllegalStateException("First event must be of type BasketEvent");
        }
        return (BasketEvent) event;
    }

    private Basket initializeBasket(String basketId, BasketEvent firstEvent) {
        return Basket.builder()
                .id(basketId)
                .userId(firstEvent.getUserId())
                .items(new HashMap<>())
                .status(BasketStatus.OPEN)
                .version(0)
                .lastModified(firstEvent.getTimestamp())
                .build();
    }

    private void applyEvent(Basket basket, BasketEvent event) {
        switch (event.getType()) {
            case "BASKET_CREATED" -> {}
            case "ITEM_ADDED" -> handleItemAdded(basket, (ItemAddedEvent) event);
            case "ITEM_REMOVED" -> handleItemRemoved(basket, (ItemRemovedEvent) event);
            case "BASKET_CONFIRMED" -> basket.setStatus(BasketStatus.CONFIRMED);
            case "BASKET_EXPIRED" -> basket.setStatus(BasketStatus.EXPIRED);
            case "ITEM_QUANTITY_UPDATED" -> handleQuantityUpdate(basket, (ItemQuantityUpdatedEvent) event);
            default -> throw new IllegalArgumentException("Unknown event type: " + event.getType());
        }

        basket.setVersion(event.getVersion());
        basket.setLastModified(event.getTimestamp());
    }

    private void handleItemAdded(Basket basket, ItemAddedEvent event) {
        BasketItem item = BasketItem.builder()
                .productId(event.getProductId())
                .productName(event.getProductName())
                .quantity(event.getQuantity())
                .price(event.getPrice())
                .build();
        basket.getItems().put(event.getProductId(), item);
    }

    private void handleItemRemoved(Basket basket, ItemRemovedEvent event) {
        basket.getItems().remove(event.getProductId());
    }

    private void handleQuantityUpdate(Basket basket, ItemQuantityUpdatedEvent event) {
        BasketItem existingItem = basket.getItems().get(event.getProductId());
        if (existingItem != null) {
            BasketItem updatedItem = existingItem.withQuantity(event.getQuantity());
            basket.getItems().put(event.getProductId(), updatedItem);
        }
    }

}
