package com.ztp.basket_service.domain.service;

import com.ztp.basket_service.domain.event.*;
import com.ztp.basket_service.domain.exception.BasketAccessDeniedException;
import com.ztp.basket_service.domain.exception.BasketNotOpenException;
import com.ztp.basket_service.domain.model.Basket;
import com.ztp.basket_service.domain.model.BasketStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BasketService {
    private final BasketEventSourcingService eventSourcingService;

    public String createBasket(String userId) {
        String basketId = UUID.randomUUID().toString();
        BasketCreatedEvent event = new BasketCreatedEvent(basketId, userId, 1);
        eventSourcingService.appendEvent(event);
        return basketId;
    }

    public Basket getBasket(String basketId) {
        return eventSourcingService.reconstructBasket(basketId);
    }

    public Basket getBasketForUser(String basketId, String userId) {
        Basket basket = getBasket(basketId);
        if(!basket.getUserId().equals(userId)) {
            throw new BasketAccessDeniedException(basketId, userId);
        }
        return basket;
    }

    public void addItem(String basketId, String userId, Long productId, String productName,
                        int quantity, double price) {
        Basket basket = getBasketForUser(basketId, userId);
        validateBasketIsOpen(basket);

        ItemAddedEvent event = new ItemAddedEvent(basketId, userId, productId, productName,
                quantity, price, basket.getVersion() + 1);
        eventSourcingService.appendEvent(event);
    }

    public void removeItem(String basketId, String userId, Long productId) {
        Basket basket = getBasketForUser(basketId, userId);
        validateBasketIsOpen(basket);

        ItemRemovedEvent event = new ItemRemovedEvent(basketId, userId, productId, basket.getVersion() + 1);
        eventSourcingService.appendEvent(event);
    }

    public void updateItemQuantity(String basketId, String userId, Long productId, int quantity) {
        Basket basket = getBasketForUser(basketId, userId);
        validateBasketIsOpen(basket);

        ItemQuantityUpdatedEvent event = new ItemQuantityUpdatedEvent(
                basketId, userId, productId, quantity, basket.getVersion() + 1
        );
        eventSourcingService.appendEvent(event);
    }

    public void confirmBasket(String basketId, String userId) {
        Basket basket = getBasketForUser(basketId, userId);
        validateBasketIsOpen(basket);

        BasketConfirmedEvent event = new BasketConfirmedEvent(basketId, userId, basket.getVersion() + 1);
        eventSourcingService.appendEvent(event);
    }

    private void validateBasketIsOpen(Basket basket) {
        if (basket.getStatus() != BasketStatus.OPEN) {
            throw new BasketNotOpenException(basket.getId());
        }
    }
}
