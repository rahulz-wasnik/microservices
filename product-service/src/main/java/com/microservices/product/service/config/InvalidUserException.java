package com.microservices.product.service.config;

public class InvalidUserException extends Exception {

    public InvalidUserException() {
        super("Invalid user login");
    }
}
