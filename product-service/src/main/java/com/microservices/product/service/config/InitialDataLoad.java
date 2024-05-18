package com.microservices.product.service.config;

import com.github.javafaker.Faker;
import com.microservices.product.service.dto.ProductDto;
import com.microservices.product.service.entity.Product;
import com.microservices.product.service.entity.Quality;
import com.microservices.product.service.repository.ProductRepository;
import com.microservices.product.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitialDataLoad implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Faker faker;

    @Autowired
    private ProductService productService;

    @Override
    public void run(String... args) throws Exception {

        productRepository.deleteAll();

        List<Product> products = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            /*
              Added qualities here to only understand hibernate N+1 problem and the way to resolve it.
             */
            // List<Quality> qualities = List.of(Quality.builder().rating("One").build(), Quality.builder().rating("Two").build());
            products.add(Product.builder().name(faker.name().name()).quantity(faker.random().nextInt(0, 10)).build());
        }

        productRepository.saveAll(products);
        productService.findById(1L);
    }

}
