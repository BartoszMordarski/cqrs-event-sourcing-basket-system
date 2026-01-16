package com.ztp.basket_service.domain.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BasketExpiredEvent extends BasketEvent {
    public BasketExpiredEvent(String basketId, String userId, int version) {
        super(basketId, userId, "BASKET_EXPIRED", version);
    }
} 