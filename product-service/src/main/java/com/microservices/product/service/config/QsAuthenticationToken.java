package com.microservices.product.service;

import com.microservices.product.service.config.QsUserPrincipal;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class QsAuthenticationToken extends AbstractAuthenticationToken {

    private QsUserPrincipal principal;

    public QsAuthenticationToken(Collection<? extends GrantedAuthority> authorities, QsUserPrincipal principal) {
        super(authorities);
        this.principal = principal;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

}
