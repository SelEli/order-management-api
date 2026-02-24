package com.ordermanagement.api.integration;

import com.ordermanagement.api.entity.Product;
import com.ordermanagement.api.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProductIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldSaveAndRetrieveProduct() {
        Product p = Product.builder()
                .name("Keyboard")
                .price(49.99)
                .build();

        Product saved = productRepository.save(p);

        assertNotNull(saved.getId());

        Product found = productRepository.findById(saved.getId())
                .orElseThrow();

        assertEquals("Keyboard", found.getName());
        assertEquals(49.99, found.getPrice());
    }
}
