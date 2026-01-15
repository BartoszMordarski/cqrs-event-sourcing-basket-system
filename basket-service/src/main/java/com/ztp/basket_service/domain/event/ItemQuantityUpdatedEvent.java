package com.ztp.basket_service.domain.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemQuantityUpdatedEvent extends BasketEvent {
    private Long productId;
    private int quantity;

    public ItemQuantityUpdatedEvent(String basketId, String userId, Long productId, int quantity, int version) {
        super(basketId, userId, "ITEM_QUANTITY_UPDATED", version);
        this.productId = productId;
        this.quantity = quantity;
    }
} 