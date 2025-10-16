// src/main/java/com/parcial/dos/parcialdos/account/dto/AccountRespuestaDTO.java
package com.parcial.dos.parcialdos.account.dto;

import java.math.BigDecimal;

public class AccountRespuestaDTO {
    private Long id;
    private String numeroCuenta;
    private String dueno;
    private BigDecimal balanceActual;
    private boolean active;

   
    public AccountRespuestaDTO() {
    }

    private AccountRespuestaDTO(Long id,
                                String numeroCuenta,
                                String dueno,
                                BigDecimal balanceActual,
                                boolean active) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("El ID no puede ser nulo ni negativo");
        }
        if (numeroCuenta == null || numeroCuenta.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de cuenta no puede estar vacío");
        }
        if (dueno == null || dueno.trim().isEmpty()) {
            throw new IllegalArgumentException("El dueño no puede estar vacío");
        }
        if (balanceActual == null) {
            throw new IllegalArgumentException("El saldo no puede ser nulo");
        }
        this.id             = id;
        this.numeroCuenta   = numeroCuenta;
        this.dueno          = dueno;
        this.balanceActual  = balanceActual;
        this.active         = active;
    }

    public static AccountRespuestaDTO createInstance(Long id,
                                                     String numeroCuenta,
                                                     String dueno,
                                                     BigDecimal balanceActual,
                                                     boolean active) {
        return new AccountRespuestaDTO(id, numeroCuenta, dueno, balanceActual, active);
    }

 
    public Long getId() {
        return id;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getDueno() {
        return dueno;
    }

    public BigDecimal getBalanceActual() {
        return balanceActual;
    }

    public boolean isActive() {
        return active;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }

    public void setBalanceActual(BigDecimal balanceActual) {
        this.balanceActual = balanceActual;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    
    public String obtenerRepresentacion() {
        return "ID: " + id +
               ", Cuenta: " + numeroCuenta +
               ", Dueño: " + dueno +
               ", Saldo: " + balanceActual +
               ", Activa: " + active;
    }
}