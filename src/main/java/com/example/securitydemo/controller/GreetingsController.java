package com.example.securitydemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello";
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String userApi(){
        return "Hello user";
    }
    @GetMapping("/admin")
    public String adminApi(){
        return "Hello admin";
    }
}
