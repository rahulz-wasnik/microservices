package com.microservices.securityconfig.controller;

import com.microservices.securityconfig.dto.AuthDto;
import com.microservices.securityconfig.dto.TokenDto;
import com.microservices.securityconfig.service.JwtService;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class TokenController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/token")
    public ResponseEntity<?> generate(@RequestBody AuthDto authDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authDto.getUserName(),
                authDto.getPassword()));
        if (authenticate.isAuthenticated()) {
            return ResponseEntity.ok(new TokenDto(jwtService.generateToken(authDto.getUserName())));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam("token") String token) {
        try {
            jwtService.validateToken(token);
        } catch (SignatureException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
       return ResponseEntity.ok().build();
    }
}
