package com.ztp.product_service.infrastructure;

import com.ztp.product_service.domain.model.Product;
import com.ztp.product_service.domain.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Configuration
public class ProductDataLoader {

    @Bean
    public CommandLineRunner loadData(ProductRepository productRepository) {
        return args -> {
            productRepository.deleteAll();

            List<Product> products = Arrays.asList(
                    Product.builder()
                            .name("iPhone 17")
                            .price(999.99)
                            .availableQuantity(50)
                            .build(),
                    Product.builder()
                            .name("Samsung Galaxy S23")
                            .price(899.99)
                            .availableQuantity(100)
                            .build(),
                    Product.builder()
                            .name("MacBook Pro")
                            .price(1499.99)
                            .availableQuantity(100)
                            .build(),
                    Product.builder()
                            .name("AirPods Pro")
                            .price(249.99)
                            .availableQuantity(100)
                            .build(),
                    Product.builder()
                            .name("iPad Air")
                            .price(599.99)
                            .availableQuantity(100)
                            .build()
            );

            productRepository.saveAll(products);
        };
    }
}
