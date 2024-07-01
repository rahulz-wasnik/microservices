package com.microservices.product.service.config;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.security.auth.Subject;
import java.security.Principal;
import java.util.Collections;
import java.util.Set;

@Builder
@ToString
public class QsUserPrincipal implements Principal {

    @Getter
    private String name;

    @Getter
    private String email;

    private Set<String> roles;

    public Set<String> getRoles() {
        return roles == null ? Collections.emptySet() : roles;
    }

    @Override
    public boolean implies(Subject subject) {
        return Principal.super.implies(subject);
    }
}
