package com.microservices.payment.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/payments")
public class PaymentsController {

	@PostMapping
	public ResponseEntity<Boolean> makePayment() {
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}
