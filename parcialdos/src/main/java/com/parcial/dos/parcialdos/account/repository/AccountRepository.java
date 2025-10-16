package com.parcial.dos.parcialdos.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parcial.dos.parcialdos.account.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
}
