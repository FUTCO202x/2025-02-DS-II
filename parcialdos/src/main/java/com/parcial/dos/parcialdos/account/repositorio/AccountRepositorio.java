package com.parcial.dos.parcialdos.account.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parcial.dos.parcialdos.account.entity.Account;

@Repository
public interface AccountRepositorio extends JpaRepository<Account, Long> {
    Account findByAccountNumber(String accountNumber);
}
