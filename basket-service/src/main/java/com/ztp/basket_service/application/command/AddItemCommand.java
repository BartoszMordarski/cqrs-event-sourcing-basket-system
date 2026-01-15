package com.ztp.basket_service.application.command;

import lombok.Builder;

@Builder
public record AddItemCommand(
        String basketId,
        String userId,
        Long productId,
        int quantity) { }
