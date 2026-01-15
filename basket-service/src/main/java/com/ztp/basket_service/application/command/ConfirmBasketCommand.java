package com.ztp.basket_service.application.command;

import lombok.Builder;

@Builder
public record ConfirmBasketCommand(String basketId, String userId) { }