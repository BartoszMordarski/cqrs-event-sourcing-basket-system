package com.ztp.basket_service.application.query;

import com.ztp.basket_service.api.dto.BasketItemResponse;
import com.ztp.basket_service.api.dto.BasketResponse;
import com.ztp.basket_service.domain.model.Basket;
import com.ztp.basket_service.domain.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetBasketQueryHandler {
    private final BasketService basketService;

    public BasketResponse handle(GetBasketQuery query) {
        Basket basket = basketService.getBasketForUser(query.basketId(), query.userId());

        List<BasketItemResponse> itemResponses = basket.getItems().values().stream()
                .map(item -> BasketItemResponse.builder()
                        .productId(item.getProductId())
                        .productName(item.getProductName())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .totalPrice(item.getPrice() * item.getQuantity())
                        .build())
                .toList();

        return BasketResponse.builder()
                .basketId(basket.getId())
                .userId(basket.getUserId())
                .items(itemResponses)
                .totalValue(basket.getTotalValue())
                .status(basket.getStatus().toString())
                .build();
    }
}
