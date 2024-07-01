package com.microservices.product.service.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.microservices.product.service.QsAuthenticationToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    private final String AUTHORIZATION = "Authorization";
    private final String BEARER_WITH_SPACE_AFTER = "Bearer ";
    private final String CUSTOM_APP_ROLES = "custom:app_roles";
    private final String ROLE_ = "ROLE_";
    public final List<UserInfoResolvers> userInfoResolvers;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String bearerTokenHolder = request.getHeader(AUTHORIZATION);
        DecodedJWT decodedJWT = extractToken(bearerTokenHolder).map(JWT::decode).orElseThrow(() -> new InvalidUserException());
        JwtUserInfo jwtUserInfo = extractUserInfo(decodedJWT);
        Authentication authentication = toAuthentication(jwtUserInfo);
        authentication.setAuthenticated(true);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private JwtUserInfo extractUserInfo(DecodedJWT decodedJWT) throws InvalidUserException {
        Claim roles = decodedJWT.getClaim(CUSTOM_APP_ROLES);

        if (roles.isMissing()) {
            throw new InvalidUserException();
        }

        Set<String> appRoles = extractRoles(roles.asString());
        return appRoles.stream().map(role -> userInfoResolvers.stream()
                .filter(resolver -> resolver.canHandle(role))
                .map(resolver -> resolver.resolve(decodedJWT))
                .findAny()
                .orElse(null) // TODO: throw an exception instead
                ).filter(userInfo -> Objects.nonNull(userInfo))
                .findFirst().orElseGet(() -> {
                    log.error("No user info found from the JWT token");
                    return new JwtUserInfo(null, null, null);
                });
    }

    private Optional<String> extractToken(String bearerTokenHolder) {
        return bearerTokenHolder != null && bearerTokenHolder.startsWith(BEARER_WITH_SPACE_AFTER) ?
                Optional.of(bearerTokenHolder.substring(7)) : Optional.empty();
    }

    private Set<String> extractRoles(String roles) {
        return Arrays.stream(roles.split(",")).map(String::trim).collect(Collectors.toSet());
    }

    private QsAuthenticationToken toAuthentication(JwtUserInfo jwtUserInfo) {
        List<SimpleGrantedAuthority> authorities = toAuthorities(jwtUserInfo.roles());
        QsUserPrincipal principal = toPrincipal(jwtUserInfo);
        return new QsAuthenticationToken(authorities, principal);
    }

    private List<SimpleGrantedAuthority> toAuthorities(String roles) {
        return Arrays.stream(roles.split(","))
                .map(role -> new SimpleGrantedAuthority(ROLE_+role.trim()))
                .toList();
    }

    private QsUserPrincipal toPrincipal(JwtUserInfo jwtUserInfo) {
        return QsUserPrincipal.builder()
                .name(jwtUserInfo.userName())
                .email(jwtUserInfo.email())
                .roles(extractRoles(jwtUserInfo.roles()))
                .build();
    }
}
