package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.UsuarioResponse;
import com.example.demo.dtos.UsuarioResponseNameLastname;
import com.example.demo.model.Usuario;
import com.example.demo.service.IServiceUsuario;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final IServiceUsuario serviceUsuario;

    public UsuarioController(IServiceUsuario serviceUsuario) {
        this.serviceUsuario=serviceUsuario;
    }

    @PostMapping
    public UsuarioResponse create(@RequestBody Usuario usuario) {        
        return serviceUsuario.create(usuario);
    }
    
    @GetMapping()
    public List<Usuario> getAllUser() {
        return serviceUsuario.getAllUser();
    }
    
    @GetMapping("/{id}")
    public Usuario getUserById(@PathVariable("id") int id) {
        return serviceUsuario.getUserById(id);
    }

    @PostMapping("/params")
    public UsuarioResponseNameLastname getUserByNameAndSurname(@RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido) {
        return serviceUsuario.getUserByNameAndSurname(nombre, apellido);
    }
    
}
