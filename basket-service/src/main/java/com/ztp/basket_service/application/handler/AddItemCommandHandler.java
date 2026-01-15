package com.ztp.basket_service.application.handler;

import com.ztp.basket_service.api.dto.ProductServiceResponse;
import com.ztp.basket_service.application.command.AddItemCommand;
import com.ztp.basket_service.domain.service.BasketService;
import com.ztp.basket_service.domain.service.ProductServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddItemCommandHandler {
    private final BasketService basketService;
    private final ProductServiceClient productServiceClient;

    public void handle(AddItemCommand command) {
        ProductServiceResponse product = productServiceClient.getProductById(command.productId());

        basketService.addItem(
                command.basketId(),
                command.userId(),
                product.getId(),
                product.getName(),
                command.quantity(),
                product.getPrice()
        );

        productServiceClient.decreaseQuantity(product.getId(), command.quantity());
    }
}
