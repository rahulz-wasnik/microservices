package com.microservices.product.service.config;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.user.NoOpUserProvider;
import org.togglz.core.user.UserProvider;

@Configuration
public class FakerConfig {

    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean
    public UserProvider userProvider() {
        return new NoOpUserProvider();
    }
}
