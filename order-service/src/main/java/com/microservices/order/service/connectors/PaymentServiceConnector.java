package com.microservices.order.service.connectors;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "PAYMENT-SERVICE/payments")
public interface PaymentServiceConnector {

    @PostMapping
    ResponseEntity<Boolean> makePayment();
}
