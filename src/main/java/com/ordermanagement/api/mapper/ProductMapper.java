package com.ordermanagement.api.mapper;

import com.ordermanagement.api.dto.product.ProductResponse;
import com.ordermanagement.api.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice());
    }
}
