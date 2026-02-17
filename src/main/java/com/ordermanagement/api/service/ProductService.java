package com.ordermanagement.api.service;

import com.ordermanagement.api.dto.product.ProductRequest;
import com.ordermanagement.api.entity.Product;
import com.ordermanagement.api.exception.ResourceNotFoundException;
import com.ordermanagement.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repo;

    public Product create(ProductRequest req) {
        return repo.save(Product.builder()
                .name(req.name())
                .price(req.price())
                .build());
    }

    public Product get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
}
