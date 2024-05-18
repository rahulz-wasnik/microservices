package com.microservices.product.service.service;

import com.microservices.product.service.entity.Product;
import com.microservices.product.service.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @DisplayName("Get all products")
    @Test
    void it_should_get_all_products() {
        Mockito.when(productRepository.findAll()).thenReturn(List.of(Product.builder().id(1L).build()));

        List<Product> products = productService.getAllProducts();

        Assertions.assertNotNull(products);
    }
}