package com.microservices.product.service.config;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductOwnerResolver implements UserInfoResolvers {

    @Override
    public JwtUserInfo resolve(DecodedJWT decodedJWT) {
        String userName = decodedJWT.getClaim("cognito:username").asString().trim();
        String email = decodedJWT.getClaim("email").asString().trim();
        Claim roles = decodedJWT.getClaim("custom:app_roles");

        return new JwtUserInfo(userName, email, roles.asString().trim());
    }

    @Override
    public boolean canHandle(String role) {
        return UserRole.PRODUCT_OWNER.toString().equals(role);
    }
}
