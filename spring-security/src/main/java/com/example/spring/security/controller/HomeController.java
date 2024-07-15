package com.example.spring.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "Hello World";
    }

    @GetMapping("/admin/home")
    public String adminHome() {
        return "Admin Home";
    }

    @GetMapping("/user/home")
    public String userHome() {
        return "User Home";
    }

    @GetMapping("/getName")
    public String getUser(Principal principal)
    {
        return principal.getName();
    }
}
