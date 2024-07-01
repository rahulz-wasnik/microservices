package com.microservices.product.service.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class AuthConfig {

    @EnableGlobalMethodSecurity(prePostEnabled = true)
    @ConditionalOnProperty(name = "qs.web.security.method-security.enabled", havingValue = "true")
    static class MethodSecurityConfig {

    }

    @Bean
    @ConditionalOnProperty(name = "qs.web.security.config.interceptor.enabled", havingValue = "true")
    public InterceptorConfig interceptorConfig(List<UserInfoResolvers> userInfoResolvers) {
        return new InterceptorConfig(userInfoResolvers);
    }

    @Bean
    public SecurityFilterChain filterChainLocal(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/**")
                .permitAll();
//                .and()
//                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
