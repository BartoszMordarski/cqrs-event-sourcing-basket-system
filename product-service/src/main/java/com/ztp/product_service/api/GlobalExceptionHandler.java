package com.ztp.product_service.api;
import com.ztp.product_service.domain.exception.InsufficientQuantityException;
import com.ztp.product_service.domain.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFound(ProductNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("Error", ex.getMessage()));
    }

    @ExceptionHandler(InsufficientQuantityException.class)
    public ResponseEntity<Map<String, Object>> handleInsufficientQuantity(InsufficientQuantityException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("Error", ex.getMessage()));
    }

}
