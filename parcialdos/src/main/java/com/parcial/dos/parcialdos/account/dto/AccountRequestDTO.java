// src/main/java/com/parcial/dos/parcialdos/account/dto/AccountRequestDTO.java
package com.parcial.dos.parcialdos.account.dto;

import java.math.BigDecimal;

public class AccountRequestDTO {
    private String numeroCuenta;
    private String dueno;
    private BigDecimal balanceActual;

    /**
     * Constructor público sin argumentos: necesario para
     * que Jackson pueda deserializar el JSON de la petición.
     */
    public AccountRequestDTO() {
    }

    private AccountRequestDTO(String numeroCuenta, String dueno, BigDecimal balanceActual) {
        if (numeroCuenta == null || numeroCuenta.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de cuenta no puede estar vacío");
        }
        if (dueno == null || dueno.trim().isEmpty()) {
            throw new IllegalArgumentException("El dueño no puede estar vacío");
        }
        if (balanceActual == null || balanceActual.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El saldo no puede ser nulo ni negativo");
        }
        this.numeroCuenta  = numeroCuenta;
        this.dueno         = dueno;
        this.balanceActual = balanceActual;
    }

    public static AccountRequestDTO createInstance(String numeroCuenta, String dueno, BigDecimal balanceActual) {
        return new AccountRequestDTO(numeroCuenta, dueno, balanceActual);
    }

    // Getters JavaBean para que Jackson serialice y para el servicio
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getDueno() {
        return dueno;
    }

    public BigDecimal getBalanceActual() {
        return balanceActual;
    }

    // Setters necesarios para deserializar el cuerpo JSON de la petición
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }

    public void setBalanceActual(BigDecimal balanceActual) {
        this.balanceActual = balanceActual;
    }

    // Validación auxiliar
    public boolean sonDatosValidos() {
        return numeroCuenta  != null && !numeroCuenta.trim().isEmpty() &&
               dueno         != null && !dueno.trim().isEmpty() &&
               balanceActual != null && balanceActual.compareTo(BigDecimal.ZERO) >= 0;
    }
}
