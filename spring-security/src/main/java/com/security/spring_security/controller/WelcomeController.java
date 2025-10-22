package com.security.spring_security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api")
public class WelcomeController {

//    @Value("${spring.security.user.name}")
//    private String username="Abi";
//
//    @Value("${spring.security.user.password}")
//    private String password="55555";
//
//    public WelcomeController() {
//        System.out.println(username.concat(" ").concat(password));
//    }

    @GetMapping("/welcome")
    public String Welcome() {
        return "Bienvenido al mundo de la seguridad";
    }
    

}
