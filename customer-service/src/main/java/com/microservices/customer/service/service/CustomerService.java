package com.microservices.customer.service.service;

import com.microservices.customer.service.dto.CustomerDto;
import com.microservices.customer.service.entity.CustomerEntity;
import com.microservices.customer.service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerDto> getCustomers() {
        return customerRepository.findAll().stream().map(this::mapToDto).toList();
    }

    private CustomerDto mapToDto(CustomerEntity customerEntity) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customerEntity.getId());
        customerDto.setFirstName(customerEntity.getFirstName());
        return customerDto;
    }
}
