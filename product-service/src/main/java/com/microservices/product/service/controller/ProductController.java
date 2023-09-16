package com.microservices.product.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/products")
public class ProductController {

	@PostMapping(path = "/check-quantity/{productId}")
	public ResponseEntity<Boolean> checkQuantity(@PathVariable Integer productId) {
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}
