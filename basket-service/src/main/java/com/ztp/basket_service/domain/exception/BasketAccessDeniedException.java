package com.ztp.basket_service.domain.exception;

public class BasketAccessDeniedException extends RuntimeException{
    public BasketAccessDeniedException(String basketId, String userId) {
        super(String.format("User %s does not have access to basket with id: %s", userId, basketId));
    }
}
