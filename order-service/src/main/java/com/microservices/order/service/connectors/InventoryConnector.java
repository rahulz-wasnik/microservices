package com.microservices.order.service.connectors;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// TODO: Figure out how to put these service names in a property file
@FeignClient(name = "PRODUCT-SERVICE/inventory")
public interface InventoryConnector {

	@PostMapping(path = "/check-quantity/{productId}")
	public boolean checkQuantity(@PathVariable long productId, @RequestParam long quantity);
}

