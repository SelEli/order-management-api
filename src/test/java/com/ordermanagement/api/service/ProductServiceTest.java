package com.ordermanagement.api.service;

import com.ordermanagement.api.dto.product.ProductRequest;
import com.ordermanagement.api.entity.Product;
import com.ordermanagement.api.exception.ResourceNotFoundException;
import com.ordermanagement.api.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductRepository repo;
    private ProductService service;

    @BeforeEach
    void setup() {
        repo = mock(ProductRepository.class);
        service = new ProductService(repo);
    }

    @Test
    void create_shouldSaveProduct() {
        ProductRequest req = new ProductRequest("Laptop", 999.99);

        when(repo.save(any(Product.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Product product = service.create(req);

        assertEquals("Laptop", product.getName());
        assertEquals(999.99, product.getPrice());
    }

    @Test
    void get_shouldReturnProduct_whenExists() {
        Product p = Product.builder().id(1L).name("Phone").price(500.0).build();

        when(repo.findById(1L)).thenReturn(Optional.of(p));

        Product result = service.get(1L);

        assertEquals("Phone", result.getName());
    }

    @Test
    void get_shouldThrow_whenNotFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.get(1L));
    }
}
