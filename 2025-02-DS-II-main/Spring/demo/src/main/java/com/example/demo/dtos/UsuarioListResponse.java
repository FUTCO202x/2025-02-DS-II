package com.example.demo.dtos;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Usuario;

public class UsuarioListResponse {
    private static List<Usuario> listUsuario= new ArrayList<>();

    public UsuarioListResponse() {
        listUsuario.add(new Usuario(1,"user1","ape1","email1@email.com"));
        listUsuario.add(new Usuario(2,"user2","ape2","email2@email.com"));
        listUsuario.add(new Usuario(3,"user3","ape3","email3@email.com"));
    }

    public List<Usuario> getListUsuario() {  
        return listUsuario;
    }

    

}
