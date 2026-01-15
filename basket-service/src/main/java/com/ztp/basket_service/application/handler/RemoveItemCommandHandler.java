package com.ztp.basket_service.application.handler;

import com.ztp.basket_service.application.command.RemoveItemCommand;
import com.ztp.basket_service.domain.exception.InsufficientQuantityException;
import com.ztp.basket_service.domain.model.Basket;
import com.ztp.basket_service.domain.model.BasketItem;
import com.ztp.basket_service.domain.service.BasketService;
import com.ztp.basket_service.domain.service.ProductServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RemoveItemCommandHandler {
    private final BasketService basketService;
    private final ProductServiceClient productServiceClient;

    public void handle(RemoveItemCommand command) {
        Basket basket = basketService.getBasketForUser(command.basketId(), command.userId());
        BasketItem item = basket.getItems().get(command.productId());
        
        if (item != null) {
            basketService.removeItem(
                command.basketId(),
                command.userId(),
                command.productId()
            );

            productServiceClient.increaseQuantity(command.productId(), item.getQuantity());
        } else {
            throw new InsufficientQuantityException("There is no such item in the basket");
        }
    }
} 