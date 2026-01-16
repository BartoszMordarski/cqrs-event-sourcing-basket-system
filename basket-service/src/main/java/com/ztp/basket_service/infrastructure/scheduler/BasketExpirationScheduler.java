package com.ztp.basket_service.infrastructure.scheduler;

import com.ztp.basket_service.domain.event.BasketExpiredEvent;
import com.ztp.basket_service.domain.model.Basket;
import com.ztp.basket_service.domain.model.BasketItem;
import com.ztp.basket_service.domain.service.BasketEventSourcingService;
import com.ztp.basket_service.domain.service.ProductServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class BasketExpirationScheduler {
    private final BasketEventSourcingService basketEventSourcingService;
    private final ProductServiceClient productServiceClient;

    @Value("${basket.expiration.minutes}")
    private int expirationMinutes;

    @Scheduled(fixedRateString = "${basket.expiration.check.interval:30000}")
    public void checkExpiredBaskets() {
        log.info("Checking baskets for potential expirations");
        Instant expirationThreshold = Instant.now().minus(expirationMinutes, ChronoUnit.MINUTES);

        List<Basket> openBaskets = basketEventSourcingService.findOpenBaskets();
        for (Basket basket : openBaskets) {
            if (basket.getLastModified().isBefore(expirationThreshold)) {
                for (BasketItem item : basket.getItems().values()) {
                    try {
                        productServiceClient.increaseQuantity(item.getProductId(), item.getQuantity());
                    } catch (Exception ignored) {

                    }
                }
                BasketExpiredEvent event = new BasketExpiredEvent(
                        basket.getId(),
                        basket.getUserId(),
                        basket.getVersion() + 1
                );
                basketEventSourcingService.appendEvent(event);
            }
        }
    }
}
