package com.ztp.basket_service.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddItemRequest {
    @NotNull(message = "productId is required")
    private Long productId;
    @Min(value = 1, message = "quantity must be at least 1")
    private int quantity;
} 