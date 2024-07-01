package com.microservices.product.service.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

public class InterceptorConfig implements WebMvcConfigurer {

    private List<UserInfoResolvers> userInfoResolvers;

    public InterceptorConfig(List<UserInfoResolvers> userInfoResolvers) {
        this.userInfoResolvers = userInfoResolvers;
    }

    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(new AuthInterceptor(userInfoResolvers)).addPathPatterns("/api/**");
    }
}
