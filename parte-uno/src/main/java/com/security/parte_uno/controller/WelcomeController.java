package com.security.parte_uno.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
//@RequestMapping("/api")
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Bienvenido";
    }
    
}
