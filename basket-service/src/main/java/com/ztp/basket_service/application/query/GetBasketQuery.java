package com.ztp.basket_service.application.query;

import lombok.Builder;

@Builder
public record GetBasketQuery(String basketId, String userId) { }
