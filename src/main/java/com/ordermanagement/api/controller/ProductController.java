package com.ordermanagement.api.controller;

import com.ordermanagement.api.dto.product.ProductRequest;
import com.ordermanagement.api.dto.product.ProductResponse;
import com.ordermanagement.api.mapper.ProductMapper;
import com.ordermanagement.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ProductMapper mapper;

    @PostMapping
    public ProductResponse create(@RequestBody ProductRequest req) {
        return mapper.toResponse(service.create(req));
    }
}
