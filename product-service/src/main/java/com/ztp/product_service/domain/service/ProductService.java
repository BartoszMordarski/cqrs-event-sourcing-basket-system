package com.ztp.product_service.domain.service;

import com.ztp.product_service.domain.exception.InsufficientQuantityException;
import com.ztp.product_service.domain.model.Product;
import com.ztp.product_service.domain.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id = %s".formatted(productId)));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public void reserve(Long productId, int quantity) {
        Product product = getProduct(productId);

        if (!product.hasAvailableQuantity(quantity)) {
            throw new InsufficientQuantityException(productId, quantity, product.getAvailableQuantity());
        }

        product.decreaseQuantity(quantity);
        productRepository.save(product);
    }

    @Transactional
    public void release(Long productId, int quantity) {
        Product product = getProduct(productId);
        product.increaseQuantity(quantity);
        productRepository.save(product);
    }



}
