package com.microservices.order.service.data;

import com.microservices.order.service.entity.Product;
import com.microservices.order.service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OnBootupSaveProductData implements ApplicationRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Product torch = new Product();
        torch.setName("Torch");

        Product candleSet = new Product();
        candleSet.setName("Candles 10 pack");

        productRepository.saveAll(List.of(torch, candleSet));
    }
}
