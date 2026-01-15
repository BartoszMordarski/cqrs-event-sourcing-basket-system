package com.ztp.basket_service.application.handler;


import com.ztp.basket_service.application.command.ConfirmBasketCommand;
import com.ztp.basket_service.domain.model.Basket;
import com.ztp.basket_service.domain.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConfirmBasketCommandHandler {
    private final BasketService basketService;

    public void handle(ConfirmBasketCommand command) {
        Basket basket = basketService.getBasketForUser(command.basketId(), command.userId());

        if (basket.isEmpty()) {
            throw new IllegalArgumentException("Cannot confirm empty basket");
        }

        basketService.confirmBasket(
            command.basketId(),
            command.userId()
        );
    }
} 