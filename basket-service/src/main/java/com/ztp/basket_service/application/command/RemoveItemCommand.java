package com.ztp.basket_service.application.command;

import lombok.Builder;

@Builder
public record RemoveItemCommand(String basketId, String userId, Long productId) { }