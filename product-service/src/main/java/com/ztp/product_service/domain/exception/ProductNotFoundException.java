package com.ztp.product_service.domain.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long productId) {
        super(String.format("Product with id=%s was not found", productId));
    }
}
