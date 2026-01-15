package com.ztp.basket_service.domain.service;

import com.ztp.basket_service.api.dto.ProductServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ProductServiceClient {
    private final RestTemplate restTemplate;

    @Value("${product.service.base-url}")
    private String PRODUCT_SERVICE_BASE_URL;

    public ProductServiceResponse getProductById(Long productId) {
        String url = PRODUCT_SERVICE_BASE_URL + "/" + productId;
        return restTemplate.getForObject(url, ProductServiceResponse.class);
    }

    public void decreaseQuantity(Long productId, int quantity) {
        String url = PRODUCT_SERVICE_BASE_URL + "/" + productId + "/reserve/" + quantity;
        restTemplate.postForObject(url, null,  Void.class);
    }

    public void increaseQuantity(Long productId, int quantity) {
        String url = PRODUCT_SERVICE_BASE_URL + "/" + productId + "/release/" + quantity;
        restTemplate.postForObject(url, null,  Void.class);
    }
}
