package com.ztp.basket_service.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasketResponse {
    private String basketId;
    private String userId;
    private List<BasketItemResponse> items;
    private double totalValue;
    private String status;
} 