package com.microservices.api.gateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class Routes {

    public List<String> openApiEndpoints = List.of(
            "/auth/register",
            "/auth/token",
            "/auth/validate",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured = serverHttpRequest -> openApiEndpoints
            .stream()
            .noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
}
