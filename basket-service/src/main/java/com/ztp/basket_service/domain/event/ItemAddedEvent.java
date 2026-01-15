package com.ztp.basket_service.domain.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
public class ItemAddedEvent extends BasketEvent {
    private Long productId;
    private String productName;
    private int quantity;
    private double price;

    public ItemAddedEvent(String basketId, String userId, Long productId, String productName,
                          int quantity, double price, int version) {
        super(basketId, userId, "ITEM_ADDED", version);
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
} 