package com.ztp.basket_service.domain.model;

import lombok.*;

import java.time.Instant;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Basket {
    private String id;
    private String userId;
    private Map<Long, BasketItem> items;
    private BasketStatus status;
    private int version;
    private Instant lastModified;


    public boolean isEmpty() {
        return items == null || items.isEmpty();
    }

    public double getTotalValue() {
        if(isEmpty()) return 0.0;
        return items.values().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }
} 