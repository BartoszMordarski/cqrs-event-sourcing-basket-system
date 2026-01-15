package com.ztp.basket_service.api;

import com.ztp.basket_service.domain.exception.BasketAccessDeniedException;
import com.ztp.basket_service.domain.exception.BasketNotOpenException;
import com.ztp.basket_service.domain.exception.InsufficientQuantityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException e) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(InsufficientQuantityException.class)
    public ResponseEntity<Object> handleInsufficientProductQuantityException(InsufficientQuantityException ex) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(BasketAccessDeniedException.class)
    public ResponseEntity<Object> handleBasketAccessDeniedException(BasketAccessDeniedException ex) {
        return createErrorResponse(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(BasketNotOpenException.class)
    public ResponseEntity<Object> handleBasketNotOpenException(BasketNotOpenException ex) {
        return createErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
    }


    private ResponseEntity<Object> createErrorResponse(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }
}
