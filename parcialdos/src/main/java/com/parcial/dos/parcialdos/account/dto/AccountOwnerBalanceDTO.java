// src/main/java/com/parcial/dos/parcialdos/account/dto/AccountOwnerBalanceDTO.java
package com.parcial.dos.parcialdos.account.dto;

import java.math.BigDecimal;

public class AccountOwnerBalanceDTO {
    private String dueno;
    private BigDecimal balanceActual;

    
    public AccountOwnerBalanceDTO() {
    }

    private AccountOwnerBalanceDTO(String dueno, BigDecimal balanceActual) {
        if (dueno == null || dueno.trim().isEmpty()) {
            throw new IllegalArgumentException("El dueño no puede estar vacío");
        }
        if (balanceActual == null || balanceActual.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El saldo no puede ser nulo ni negativo");
        }
        this.dueno         = dueno;
        this.balanceActual = balanceActual;
    }

    public static AccountOwnerBalanceDTO createInstance(String dueno, BigDecimal balanceActual) {
        return new AccountOwnerBalanceDTO(dueno, balanceActual);
    }

    // Getters JavaBean para que Jackson serialice
    public String getDueno() {
        return dueno;
    }

    public BigDecimal getBalanceActual() {
        return balanceActual;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }

    public void setBalanceActual(BigDecimal balanceActual) {
        this.balanceActual = balanceActual;
    }

    // Método auxiliar de depuración
    public void mostrarDatos() {
        System.out.println("Dueño: " + dueno + ", Saldo: " + balanceActual);
    }
}
