package com.microservices.customer.service.dataload;

import com.github.javafaker.Faker;
import com.microservices.customer.service.entity.CustomerEntity;
import com.microservices.customer.service.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoad implements CommandLineRunner {

    private final Faker faker;
    private final CustomerRepository customerRepository;

    public DataLoad(Faker faker, CustomerRepository customerRepository) {
        this.faker = faker;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setFirstName(faker.name().firstName());
            customerRepository.save(customerEntity);
        }
    }
}
