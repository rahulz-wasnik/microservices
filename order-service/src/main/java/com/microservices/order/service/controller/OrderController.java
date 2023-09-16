package com.microservices.order.service.controller;

import com.microservices.order.service.entity.Order;
import com.microservices.order.service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@PostMapping
	public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
		Order savedOrder = orderService.placeOrder(order);
		return new ResponseEntity<>(savedOrder, HttpStatus.OK);
	}
}
