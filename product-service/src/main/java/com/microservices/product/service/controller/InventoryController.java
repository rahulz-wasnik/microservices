package com.microservices.product.service.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/inventory")
public class InventoryController {

	@PostMapping(path = "/check-quantity/{productId}")
	public boolean checkQuantity(@PathVariable Integer productId, @RequestParam Integer quantity) {
		return true;
	}
}
