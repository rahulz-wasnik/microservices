package com.microservices.product.service.config;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface UserInfoResolvers {
    JwtUserInfo resolve(DecodedJWT jwt);
    boolean canHandle(String role);
}
