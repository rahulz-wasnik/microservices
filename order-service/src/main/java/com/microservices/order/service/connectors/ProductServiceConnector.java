package com.microservices.order.service.connectors;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

// TODO: Figure out how to put these service names in a property file
@FeignClient(name = "PRODUCT-SERVICE/products")
public interface ProductServiceConnector {

	@PostMapping(path = "/check-quantity/{productId}")
//	@CircuitBreaker(name = "placeOrders")
//	@Retry(name = "retryPlaceOrders")
	public ResponseEntity<Boolean> checkQuantity(@PathVariable long productId);

}

