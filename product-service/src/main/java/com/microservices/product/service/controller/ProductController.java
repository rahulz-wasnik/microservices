package com.microservices.product.service.controller;

import com.microservices.product.service.dto.ProductDto;
import com.microservices.product.service.entity.Product;
import com.microservices.product.service.service.ProductService;
import com.microservices.product.service.togglz.ServiceFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path="/api/v1/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CacheManager cacheManager;

	@GetMapping(path = "/check-quantity/{id}")
	public ResponseEntity<ProductDto> checkQuantity(@PathVariable Long id) {
		ProductDto productDto = new ProductDto();
		productDto.setId(id);
		productDto.setQuantity(1000);
		return ResponseEntity.ok(productDto);
	}

	@GetMapping
//	@RateLimiter(name = "getProducts", fallbackMethod = "getProductsFallBack")
	public ResponseEntity<List<Product>> getProducts() {
		log.info("About to fetch products");
		if (ServiceFeature.PRODUCT_ACER_LAUNCH.isActive()) {
			return ResponseEntity.ok(List.of());
		} else {
			return  ResponseEntity.ok(productService.getAllProducts());
		}
	}

	public ResponseEntity<List<Product>> getProductsFallBack(Exception exception) {
		System.out.println("An exception occured triggering fallback to be executed for getProductsFallBack");
		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<ProductDto> findProductById(@PathVariable Long id) {
		return ResponseEntity.ok(productService.findById(id));
	}

	@PostMapping
	public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto) {
		return ResponseEntity.ok(productService.save(productDto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable long id) {
		return ResponseEntity.ok(productService.update(id, productDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") long id) {
		productService.delete(id);
		return ResponseEntity.accepted().build();
	}

}
