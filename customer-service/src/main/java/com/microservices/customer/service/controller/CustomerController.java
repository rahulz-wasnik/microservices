package com.microservices.customer.service.controller;

import com.microservices.customer.service.dto.CustomerDto;
import com.microservices.customer.service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomers() {
        System.out.println("About to fetch customers");
        return ResponseEntity.ok(customerService.getCustomers());
    }
}
