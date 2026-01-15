package com.ztp.basket_service.api.dto;

import lombok.Data;

@Data
public class ProductServiceResponse {
    private Long id;
    private String name;
    private double price;
    private int availableQuantity;
}