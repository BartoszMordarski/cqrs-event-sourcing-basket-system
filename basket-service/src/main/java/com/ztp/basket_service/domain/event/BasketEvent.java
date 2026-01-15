package com.ztp.basket_service.domain.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
public abstract class BasketEvent implements Event {
    private String eventId;
    private String basketId;
    private String userId;
    private String type;
    private int version;
    private Instant timestamp;

    protected BasketEvent(String basketId, String userId, String type, int version) {
        this.eventId = java.util.UUID.randomUUID().toString();
        this.basketId = basketId;
        this.userId = userId;
        this.type = type;
        this.version = version;
        this.timestamp = Instant.now();
    }
}
