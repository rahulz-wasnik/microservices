package com.microservices.payment.service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/payments")
public class PaymentsController {

	@PostMapping(path = "/check-quantity/{productId}")
	public void placeOrder(@RequestParam Integer productId) {
		
	}
}
