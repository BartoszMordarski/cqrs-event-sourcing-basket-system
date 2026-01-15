package com.ztp.basket_service.domain.exception;

public class BasketNotOpenException extends RuntimeException {
    public BasketNotOpenException(String basketId) {
        super("Basket " + basketId + " is not open for modifications");
    }
} 