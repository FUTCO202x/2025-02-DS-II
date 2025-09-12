package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dtos.UsuarioListResponse;
import com.example.demo.dtos.UsuarioResponse;
import com.example.demo.dtos.UsuarioResponseNameLastname;
import com.example.demo.model.Usuario;

@Service
public class ServiceUsuario implements IServiceUsuario{

    @Override
    public UsuarioResponse create(Usuario usuario) {
        UsuarioResponse userCreate = new UsuarioResponse();
        userCreate.setNombre(usuario.getNombre().toUpperCase());
        userCreate.setApellido(usuario.getApellido().toUpperCase());
        return userCreate;
    }

    @Override
    public List<Usuario> getAllUser() {
        UsuarioListResponse list = new UsuarioListResponse();
        return list.getListUsuario();
    }

    @Override
    public Usuario getUserById(int id) {
        UsuarioListResponse list = new UsuarioListResponse();
        for (Usuario user : list.getListUsuario()) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public UsuarioResponseNameLastname getUserByNameAndSurname(String nombre, String apellido) {
        UsuarioResponseNameLastname usuarioResponseNameLastname = new UsuarioResponseNameLastname();
        usuarioResponseNameLastname.setNombreCompleto(nombre.concat(" ").concat(apellido));
        return usuarioResponseNameLastname;
    }

}
