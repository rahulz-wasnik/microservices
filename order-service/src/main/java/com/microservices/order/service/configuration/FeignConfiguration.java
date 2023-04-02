package com.microservices.order.service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
public class FeignConfiguration {

	@Bean
	public Logger.Level loggerLevel() {
		return Logger.Level.FULL;
	}
}
