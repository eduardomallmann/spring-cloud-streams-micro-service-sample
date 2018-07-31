package com.cloud.stream.study.inventory.product;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductInventoryService {

    private final ProductInventoryRepository productInventoryRepository;

    public ProductInventoryService(ProductInventoryRepository productInventoryRepository) {
        this.productInventoryRepository = productInventoryRepository;
    }

    public void decreaseDisposability(final List<ProductSold> products) {
        products.stream().filter(Objects::nonNull).forEach(product -> {
            this.productInventoryRepository.findByCode(product.getCode())
                    .ifPresent(productInventory -> {
                        Integer disposability = productInventory.getDisposable() - product.getQuantity();
                        productInventory.setDisposable(disposability > 0 ? disposability : 0);
                        this.productInventoryRepository.save(productInventory);
                    });
        });
    }

    public void decreaseAvailiability(final List<ProductSold> products) {
        products.stream().filter(Objects::nonNull).forEach(product -> {
            this.productInventoryRepository.findByCode(product.getCode())
                    .ifPresent(productInventory -> {
                        Integer availiability = productInventory.getAvailable() - product.getQuantity();
                        productInventory.setAvailable(availiability > 0 ? availiability : 0);
                        this.productInventoryRepository.save(productInventory);
                    });
        });
    }
}
