package com.microservices.product.service.service;

import com.microservices.product.service.dto.ProductDto;
import com.microservices.product.service.entity.Product;
import com.microservices.product.service.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Cacheable(cacheNames = "product", key = "#id")
    public ProductDto findById(Long id) {
        Product savedProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(savedProduct, productDto);
        return productDto;
    }

    public ProductDto save(ProductDto productdto) {
        Product product = new Product();
        BeanUtils.copyProperties(productdto, product);
        Product savedProduct = productRepository.save(product);
        productdto.setId(savedProduct.getId());
        return productdto;
    }

    @CachePut(cacheNames = "product", key = "#id")
    public ProductDto update(Long id, ProductDto updatedProductDetails) {
        Product savedProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        savedProduct.setName(updatedProductDetails.getName());
        savedProduct.setQuantity(updatedProductDetails.getQuantity());
        updatedProductDetails.setId(savedProduct.getId());
        productRepository.save(savedProduct);
        return updatedProductDetails;
    }

    @CacheEvict(cacheNames = "product", key = "#id")
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
