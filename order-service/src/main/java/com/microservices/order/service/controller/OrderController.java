package com.microservices.order.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.order.service.connectors.InventoryConnector;
import com.microservices.order.service.vo.OrderVO;

@RestController
@RequestMapping(path="/orders")
public class OrderController {
	
	@Autowired
	private InventoryConnector inventoryConnector;

	@PostMapping(path = "/place")
	public void placeOrder(@RequestBody OrderVO orderVO) {
		boolean inventoryPresent = inventoryConnector.checkQuantity(orderVO.getProductId(), orderVO.getQuantity());
		System.out.println(inventoryPresent);
	}
}
