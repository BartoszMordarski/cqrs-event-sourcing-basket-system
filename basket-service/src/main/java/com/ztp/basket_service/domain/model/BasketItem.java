package com.ztp.basket_service.domain.model;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasketItem {
    private Long productId;
    private String productName;
    private int quantity;
    private double price;
    private Instant lockExpiration;


    public BasketItem withQuantity(int newQuantity) {
        return BasketItem.builder()
                .productId(this.productId)
                .productName(this.productName)
                .quantity(newQuantity)
                .price(this.price)
                .lockExpiration(this.lockExpiration)
                .build();
    }

} 