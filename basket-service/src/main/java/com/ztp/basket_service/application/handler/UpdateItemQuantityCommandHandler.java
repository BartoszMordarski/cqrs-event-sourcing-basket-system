package com.ztp.basket_service.application.handler;


import com.ztp.basket_service.application.command.UpdateItemQuantityCommand;
import com.ztp.basket_service.domain.model.Basket;
import com.ztp.basket_service.domain.model.BasketItem;
import com.ztp.basket_service.domain.service.BasketService;
import com.ztp.basket_service.domain.service.ProductServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateItemQuantityCommandHandler {
    private final BasketService basketService;
    private final ProductServiceClient productServiceClient;

    public void handle(UpdateItemQuantityCommand command) {
        Basket basket = basketService.getBasketForUser(command.basketId(), command.userId());

        BasketItem currentItem = basket.getItems().get(command.productId());
        if (currentItem == null) {
            throw new IllegalArgumentException("Product not found in basket");
        }
        if (command.quantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        if (command.quantity() > currentItem.getQuantity()) {
            int additionalQuantity = command.quantity() - currentItem.getQuantity();
            productServiceClient.decreaseQuantity(command.productId(), additionalQuantity);
        } else if (command.quantity() < currentItem.getQuantity()) {
            int returnedQuantity = currentItem.getQuantity() - command.quantity();
            productServiceClient.increaseQuantity(command.productId(), returnedQuantity);
        }

        basketService.updateItemQuantity(
            command.basketId(),
            command.userId(),
            command.productId(),
            command.quantity()
        );
    }
} 