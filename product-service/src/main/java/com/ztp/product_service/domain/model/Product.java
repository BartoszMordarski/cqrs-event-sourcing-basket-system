package com.ztp.product_service.domain.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private double price;

    private int availableQuantity;

    public boolean hasAvailableQuantity(int requestedQuantity) {
        return availableQuantity >= requestedQuantity;
    }

    public void decreaseQuantity(int quantity) {
        if (!hasAvailableQuantity(quantity)) {
            throw new IllegalStateException("Not enough quantity available");
        }
        this.availableQuantity -= quantity;
    }

    public void increaseQuantity(int quantity) {
        this.availableQuantity += quantity;
    }
}
