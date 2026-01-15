package com.ztp.basket_service.domain.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BasketCreatedEvent extends BasketEvent{

    public BasketCreatedEvent(String basketId, String userId, int version) {
        super(basketId, userId, "BASKET_CREATED", version);
    }
}
