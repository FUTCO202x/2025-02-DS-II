package com.parcial.dos.parcialdos.account.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "accountNumber", unique = true)
    private String accountNumber;

    @Column(name = "ownerName")
    private String ownerName;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "active")
    private Boolean active;

    public Account() {}

    public Account(String accountNumber, String ownerName, BigDecimal balance, Boolean active) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = (balance == null) ? BigDecimal.ZERO : balance;
        this.active = (active == null) ? true : active;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}