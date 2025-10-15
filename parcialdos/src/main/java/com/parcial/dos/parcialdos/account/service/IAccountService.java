package com.parcial.dos.parcialdos.account.service;

import com.parcial.dos.parcialdos.account.dto.AccountOwnerBalanceDTO;
import com.parcial.dos.parcialdos.account.dto.AccountRequestDTO;
import com.parcial.dos.parcialdos.account.dto.AccountResponseDTO;

import java.util.List;

public interface IAccountService {
    AccountResponseDTO create(AccountRequestDTO request);
    List<AccountResponseDTO> getAll();
    AccountResponseDTO getById(Long id);
    String update(Long id, AccountRequestDTO request);
    void delete(Long id);
    AccountOwnerBalanceDTO findByNumeroCuenta(String numeroCuenta);
}