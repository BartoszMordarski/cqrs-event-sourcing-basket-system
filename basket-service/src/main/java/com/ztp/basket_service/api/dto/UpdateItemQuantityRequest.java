package com.ztp.basket_service.api.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateItemQuantityRequest {
    @Min(value = 1, message = "quantity must be at least 1")
    private int quantity;
} 