package com.fodi.auth_register.controllers;

import com.fodi.auth_register.security.JwtCore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Console;
import java.security.Principal;

@RestController
@RequestMapping("/home")
public class MainController {
    @GetMapping("/user")
    public String userAccess(Principal principal) {
        if (principal == null) {
            return null;
        }
        return principal.getName();
    }
}
