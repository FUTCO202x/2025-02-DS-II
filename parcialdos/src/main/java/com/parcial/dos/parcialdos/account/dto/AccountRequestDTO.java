package com.parcial.dos.parcialdos.account.dto;

import java.math.BigDecimal;

public class AccountRequestDTO {
    private String numeroCuenta;
    private String dueno;
    private Double balanceActual;

    public AccountRequestDTO() {}

    public AccountRequestDTO(String numeroCuenta, String dueno, Double balanceActual) {
        this.numeroCuenta = numeroCuenta;
        this.dueno = dueno;
        this.balanceActual = balanceActual;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getDueno() {
        return dueno;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }

    public Double getBalanceActual() {
        return balanceActual;
    }

    public void setBalanceActual(Double balanceActual) {
        this.balanceActual = balanceActual;
    }

}
