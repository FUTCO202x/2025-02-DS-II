package com.example.demo.dtos;

public class UsuarioResponseNameLastname {
    private String nombreCompleto;

    public UsuarioResponseNameLastname() {
    }

    public UsuarioResponseNameLastname(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
