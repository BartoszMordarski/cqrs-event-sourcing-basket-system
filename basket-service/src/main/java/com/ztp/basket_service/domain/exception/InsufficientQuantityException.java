package com.ztp.basket_service.domain.exception;

public class InsufficientQuantityException extends RuntimeException {

    public InsufficientQuantityException(Long productId, int requested, int available) {
        super(String.format("Insufficient quantity available for product %s. Requested: %d, Available: %d",
                productId, requested, available));
    }

    public InsufficientQuantityException(String message) {
        super(message);
    }
}
