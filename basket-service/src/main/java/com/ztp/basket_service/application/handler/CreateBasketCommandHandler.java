package com.ztp.basket_service.application.handler;

import com.ztp.basket_service.application.command.CreateBasketCommand;
import com.ztp.basket_service.domain.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateBasketCommandHandler {
    private final BasketService basketService;

    public String handle(CreateBasketCommand command) {
        return basketService.createBasket(command.userId());
    }
}
