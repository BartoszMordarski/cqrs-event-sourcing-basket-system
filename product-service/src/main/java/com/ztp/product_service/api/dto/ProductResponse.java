package com.ztp.product_service.api.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductResponse {
    private Long id;
    private String name;
    private double price;
    private int availableQuantity;
}
