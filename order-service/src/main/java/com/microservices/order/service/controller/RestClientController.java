package com.microservices.order.service.controller;

import com.microservices.order.service.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(path="/restClient")
public class RestClientController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        return restTemplate.exchange("http://localhost:8080/products",HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductDto>>(){});
    }

    @PostMapping
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto) {
        return restTemplate.postForEntity("http://localhost:8080/products", productDto, ProductDto.class);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable long id) {

        HttpEntity<ProductDto> request = new HttpEntity<>(productDto);

        return restTemplate.exchange(
                "http://localhost:8080/products/"+id,
                HttpMethod.PUT,
                request,
                ProductDto.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {

        return restTemplate.exchange(
                "http://localhost:8080/products/"+id,
                HttpMethod.DELETE,
                null,
                Void.class, id);
    }
}
