package com.example.demo.service;

import java.util.List;

import com.example.demo.dtos.UsuarioResponse;
import com.example.demo.dtos.UsuarioResponseNameLastname;
import com.example.demo.model.Usuario;

public interface IServiceUsuario {
    UsuarioResponse create(Usuario usuario);
    List<Usuario> getAllUser();
    Usuario getUserById(int id);
    UsuarioResponseNameLastname getUserByNameAndSurname(String nombre, String apellido);
}
