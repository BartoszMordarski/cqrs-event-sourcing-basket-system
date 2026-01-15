package com.ztp.product_service.domain.repository;

import com.ztp.product_service.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
