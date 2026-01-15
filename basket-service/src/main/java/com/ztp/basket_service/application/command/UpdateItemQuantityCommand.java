package com.ztp.basket_service.application.command;

import lombok.Builder;

@Builder
public record UpdateItemQuantityCommand(String basketId, String userId, Long productId, int quantity) { }