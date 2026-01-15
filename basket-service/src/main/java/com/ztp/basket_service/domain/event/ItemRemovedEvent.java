package com.ztp.basket_service.domain.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemRemovedEvent extends BasketEvent {
    private Long productId;

    public ItemRemovedEvent(String basketId, String userId, Long productId, int version) {
        super(basketId, userId, "ITEM_REMOVED", version);
        this.productId = productId;
    }
} 