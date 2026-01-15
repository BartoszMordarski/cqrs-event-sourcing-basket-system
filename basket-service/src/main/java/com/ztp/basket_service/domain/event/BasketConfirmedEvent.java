package com.ztp.basket_service.domain.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BasketConfirmedEvent extends BasketEvent {
    public BasketConfirmedEvent(String basketId, String userId, int version) {
        super(basketId, userId, "BASKET_CONFIRMED", version);
    }
} 