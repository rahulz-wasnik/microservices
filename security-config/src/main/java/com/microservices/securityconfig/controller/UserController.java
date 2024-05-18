package com.microservices.securityconfig.controller;

import com.microservices.securityconfig.dto.UserDto;
import com.microservices.securityconfig.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public void save(@RequestBody UserDto userDto) {
        userService.save(userDto);
    }

}
