package com.ztp.product_service.api;
import com.ztp.product_service.api.dto.ProductResponse;
import com.ztp.product_service.domain.model.Product;
import com.ztp.product_service.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long productId) {
        Product product = productService.getProduct(productId);
        return ResponseEntity.ok(toResponse(product));
    }

    @PostMapping("/{productId}/reserve/{quantity}")
    public ResponseEntity<Void> reserve(@PathVariable Long productId, @PathVariable int quantity) {
        productService.reserve(productId, quantity);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{productId}/release/{quantity}")
    public ResponseEntity<Void> release(@PathVariable Long productId, @PathVariable int quantity) {
        productService.release(productId, quantity);
        return ResponseEntity.ok().build();
    }

    private ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .availableQuantity(product.getAvailableQuantity())
                .build();
    }
}

